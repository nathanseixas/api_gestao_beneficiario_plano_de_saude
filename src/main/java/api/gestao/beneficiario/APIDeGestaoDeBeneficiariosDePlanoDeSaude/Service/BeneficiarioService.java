
// REGRA DE NEGOCIOS
// AQUI SAO IMPORTADAS OS MAPPERS E OS REPOSITORYS
package api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude.Service;

import api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude.dto.BeneficiarioRequest;
import api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude.dto.BeneficiarioResponse;
import api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude.dto.DocumentoDTO;
import api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude.mapper.BeneficiarioMapper;
import api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude.model.Beneficiario;
import api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude.model.Documento;
import api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude.repository.BeneficiarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class BeneficiarioService {

    private final BeneficiarioMapper beneficiarioMapper;
    private final BeneficiarioRepository beneficiarioRepository;


    public BeneficiarioService(BeneficiarioMapper beneficiarioMapper, BeneficiarioRepository beneficiarioRepository) {
        this.beneficiarioMapper = beneficiarioMapper;
        this.beneficiarioRepository = beneficiarioRepository;
    }

    // METODO "SALVAR"
    public BeneficiarioResponse salvar(BeneficiarioRequest request) {
        Beneficiario beneficiario = beneficiarioMapper.toEntity(request);
        return beneficiarioMapper.toResponse(beneficiarioRepository.save(beneficiario));
    }

    //METODO PARA LISTAR TODOS OS BENFICIARIOS
    List<BeneficiarioResponse> listarTodos() {
        return beneficiarioRepository.findAll().stream().map(beneficiarioMapper::toResponse).toList();
    }

    // METODO PARA LISTAR OS DOCUMENTOS

    List<DocumentoDTO> listarDocumento(Long id) {
        Beneficiario beneficiario = beneficiarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Beneficiario nao encontrado"));
        return beneficiario.getDocumentos().stream().map(documento -> new DocumentoDTO(documento.getTipoDocumento(),
                documento.getDescricao())).toList();
    }

    //METODO REMOVER
    public void remover(Long id) {

        if(!beneficiarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Id do beneficiario nao encontrado");
        }
        beneficiarioRepository.deleteById(id);
    }

    //METODO PARA ATUALIZAR
    public BeneficiarioResponse atualizar(Long id, BeneficiarioRequest request) {

        Beneficiario beneficiario = beneficiarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("beneficiario nao encontrado"));
        beneficiario.setName(request.name());
        beneficiario.setDataDeNascimento(request.dataDeNascimento());
        beneficiario.getDocumentos().clear();
        for (DocumentoDTO documentoDTO : request.documentos()){
            Documento documento = new Documento();
            documento.setTipoDocumento(documentoDTO.tipoDocumento());
            documento.setDescricao(documentoDTO.descricao());
            documento.setBeneficiario(beneficiario);
            beneficiario.getDocumentos().add(documento);
        }

        return beneficiarioMapper.toResponse(beneficiarioRepository.save(beneficiario));


    }

}
