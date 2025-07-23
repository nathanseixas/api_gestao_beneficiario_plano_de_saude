// Aqui começa o pacote onde o código está localizado
package api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude.Service;

// Importações de outras partes do projeto
import api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude.dto.BeneficiarioRequest;
import api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude.dto.BeneficiarioResponse;
import api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude.dto.DocumentoDTO;
import api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude.mapper.BeneficiarioMapper;
import api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude.model.Beneficiario;
import api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude.model.Documento;
import api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude.repository.BeneficiarioRepository;

// Importa uma exceção pra quando algo não for encontrado
import jakarta.persistence.EntityNotFoundException;

// Marca essa classe como um "serviço" do Spring (regras de negócio ficam aqui)
import org.springframework.stereotype.Service;

import java.util.List;

// Esta é a classe onde ficam as regras de negócio
@Service
public class BeneficiarioService {




    // Declara dois objetos que vão ser usados: um pra converter dados e outro pra acessar o banco
    private final BeneficiarioMapper beneficiarioMapper;
    private final BeneficiarioRepository beneficiarioRepository;



    // Construtor da classe: injeta os dois objetos acima
    public BeneficiarioService(BeneficiarioMapper beneficiarioMapper, BeneficiarioRepository beneficiarioRepository) {
        this.beneficiarioMapper = beneficiarioMapper;
        this.beneficiarioRepository = beneficiarioRepository;
    }




    // Metodo que salva um novo beneficiário
    public BeneficiarioResponse salvar(BeneficiarioRequest request) {
        Beneficiario beneficiario = beneficiarioMapper.toEntity(request);// Converte o request (entrada) em entidade do banco
        return beneficiarioMapper.toResponse(beneficiarioRepository.save(beneficiario)); // Salva no banco e retorna como resposta (BeneficiarioResponse)
    }




    // Metodo que lista todos os beneficiários cadastrados
    public List<BeneficiarioResponse> listarTodos() {
        return beneficiarioRepository.findAll().stream().map(beneficiarioMapper::toResponse).toList();  // Busca todos do banco, converte pra resposta e retorna em uma lista
    }




    public List<DocumentoDTO> listarDocumento(Long id) { // Metodo que lista os documentos de um beneficiário específico
        Beneficiario beneficiario = beneficiarioRepository.findById(id) // Procura o beneficiário no banco pelo ID, se não achar, lança erro
                .orElseThrow(() -> new EntityNotFoundException("Beneficiario nao encontrado"));

        return beneficiario.getDocumentos().stream().map(documento -> new DocumentoDTO(  // Converte cada documento do beneficiário para DocumentoDTO e retorna
                documento.getTipoDocumento(), // tipo do documento (ex: RG)
                documento.getDescricao()     // descrição (ex: número do RG)
        )).toList();
    }





    public void remover(Long id) {   // Metodo que remove um beneficiário pelo ID
        if(!beneficiarioRepository.existsById(id)) {   // Verifica se existe um beneficiário com esse ID
            throw new EntityNotFoundException("Id do beneficiario nao encontrado");  // Se não existir, lança erro
        }
        beneficiarioRepository.deleteById(id); // Se existir, remove do banco
    }




    // Metodo que atualiza um beneficiário existente
    public BeneficiarioResponse atualizar(Long id, BeneficiarioRequest request) {

        Beneficiario beneficiario = beneficiarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("beneficiario nao encontrado"));  // Procura o beneficiário no banco

        // Atualiza o nome e a data de nascimento
        beneficiario.setName(request.name());
        beneficiario.setDataDeNascimento(request.dataDeNascimento());

        // Remove todos os documentos antigos
        beneficiario.getDocumentos().clear();

        // Adiciona os novos documentos
        for (DocumentoDTO documentoDTO : request.documentos()){
            Documento documento = new Documento();
            documento.setTipoDocumento(documentoDTO.tipoDocumento()); // ex: CPF, RG
            documento.setDescricao(documentoDTO.descricao());         // ex: número do documento
            documento.setBeneficiario(beneficiario); // liga o documento ao beneficiário
            beneficiario.getDocumentos().add(documento); // adiciona à lista
        }


        // Salva no banco e retorna a resposta
        return beneficiarioMapper.toResponse(beneficiarioRepository.save(beneficiario));
    }





}
