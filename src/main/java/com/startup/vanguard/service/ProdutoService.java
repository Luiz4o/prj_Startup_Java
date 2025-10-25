package com.startup.vanguard.service;

import com.startup.vanguard.dto.produto.ProdutoCreateDTO;
import com.startup.vanguard.dto.produto.ProdutoCreatedDTO;
import com.startup.vanguard.dto.produto.ProdutoResponseDTO;
import com.startup.vanguard.dto.produto.ProdutoUpdateDTO;
import com.startup.vanguard.dto.produto.ProdutoUpdatedDTO;
import com.startup.vanguard.exception.ResourceNotFoundException;
import com.startup.vanguard.exception.S3Exception;
import com.startup.vanguard.model.Produto;
import com.startup.vanguard.repository.CategoriaRepository;
import com.startup.vanguard.repository.ProdutoRepository;
import com.startup.vanguard.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;
    private final S3Service s3Service;

    public ProdutoService(ProdutoRepository produtoRepository, UsuarioRepository usuarioRepository, CategoriaRepository categoriaRepository, S3Service s3Service) {
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
        this.s3Service = s3Service;
    }

    @Value("${aws.bucket.name.foto}")
    private String getNomeBucketFoto;

    @Value("${aws.bucket.name.doc}")
    private String getNomeBucketDoc;

    public List<ProdutoResponseDTO> getAll(){
        return produtoRepository.findAll().stream()
                .map(p ->{
                    var urlFoto = s3Service.generatePresignedUrl(p.getReferenciaFoto(), getNomeBucketFoto);
                    var urlDocumento = s3Service.generatePresignedUrl(p.getReferenciaDoc(), getNomeBucketDoc);
                    return new ProdutoResponseDTO(p, urlFoto, urlDocumento);
                })
                .toList();
    }

    @Transactional
    public ProdutoCreatedDTO insertProduto(ProdutoCreateDTO produtoCreateDTO, MultipartFile foto, MultipartFile documento){
        var usuario = usuarioRepository.findById(produtoCreateDTO.idLojista())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário", produtoCreateDTO.idLojista()));

        var categoria = categoriaRepository.findById(produtoCreateDTO.idCategoria())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria", produtoCreateDTO.idCategoria()));

        var referenciaFoto = "";
        var referenciaDoc = "";

        try {
            referenciaFoto = s3Service.uploadFile(foto, getNomeBucketFoto);
        } catch (IOException ex){
            throw new S3Exception(foto.getOriginalFilename());
        }
        try {
            referenciaDoc = s3Service.uploadFile(documento, getNomeBucketDoc);
        } catch (IOException ex){
            throw new S3Exception(documento.getOriginalFilename());
        }

        var produtoCreated = produtoRepository.save(Produto.builder()
                        .nomeFoto(foto.getOriginalFilename())
                        .referenciaFoto(referenciaFoto)
                        .referenciaDoc(referenciaDoc)
                        .nomeDocumento(documento.getOriginalFilename())
                        .categoria(categoria)
                        .lojista(usuario)
                        .descricao(produtoCreateDTO.descricao())
                        .nome(produtoCreateDTO.nome())
                        .quantidadeEstoque(produtoCreateDTO.quantidadeEstoque())
                        .preco(produtoCreateDTO.preco())
                .build());

        return new ProdutoCreatedDTO(produtoCreated);
    }

    @Transactional
    public ProdutoUpdatedDTO updateProduto(ProdutoUpdateDTO produtoUpdateDTO){
        System.out.println(produtoUpdateDTO);
        var produto = produtoRepository.findById(produtoUpdateDTO.id())
                .orElseThrow(() -> new ResourceNotFoundException("Produto",produtoUpdateDTO.id()));

        if (produtoUpdateDTO.id_categoria() != null) {
            var categoria = categoriaRepository.findById(produtoUpdateDTO.id_categoria())
                    .orElseThrow(() -> new ResourceNotFoundException("Categoria",produtoUpdateDTO.id_categoria()));
            produto.setCategoria(categoria);
        }

        if (produtoUpdateDTO.nome() != null && !produtoUpdateDTO.nome().isBlank()) {
            produto.setNome(produtoUpdateDTO.nome());
        }

        if (produtoUpdateDTO.descricao() != null && !produtoUpdateDTO.descricao().isBlank()) {
            produto.setDescricao(produtoUpdateDTO.descricao());
        }

        if (produtoUpdateDTO.price() != null) {
            produto.setPreco(produtoUpdateDTO.price());
        }

        if (produtoUpdateDTO.quantidadeEstoque() != null) {
            produto.setQuantidadeEstoque(produtoUpdateDTO.quantidadeEstoque());
        }

        var produtoAtualizado = produtoRepository.save(produto);

        return new ProdutoUpdatedDTO(produtoAtualizado);
    }

    public ProdutoResponseDTO getById(Long id) {
        var produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto",id));

        var urlFoto = s3Service.generatePresignedUrl(produto.getReferenciaFoto(), getNomeBucketFoto);
        var urlDocumento = s3Service.generatePresignedUrl(produto.getReferenciaDoc(), getNomeBucketDoc);

        return new ProdutoResponseDTO(produto, urlFoto, urlDocumento);
    }

    @Transactional
    public void deleteById(Long id){
        var produto = this.getById(id);

        produtoRepository.deleteById(id);
    }
}
