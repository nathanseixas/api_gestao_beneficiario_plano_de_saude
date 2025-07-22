

//  A CLASSE MAPPER E RESPONSAVEL POR TRANSFORMAR OS DTOS EM ENTIDADES(ENTITY) E TRANSFORMAR AS ENTIDADES(ENTITY) EM RESPONSES

package api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude.mapper;


import api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude.dto.BeneficiarioRequest;
import api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude.dto.BeneficiarioResponse;
import api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude.dto.DocumentoDTO;
import api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude.model.Beneficiario;
import api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude.model.Documento;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BeneficiarioMapper {


    public Beneficiario toEntity(BeneficiarioRequest request) {
        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setName(request.nome());
        beneficiario.setTelefone(request.telefone());
        beneficiario.setDataDeNascimento(request.dataDeNascimento());
        List<Documento> documentos = request.documentos().stream().map(d -> {
            Documento doc = new Documento();
            doc.setTipoDocumento(d.tipoDocumento());
            doc.setDescricao(d.descricao());
            doc.setBeneficiario(beneficiario);
            return doc;
        }).toList();
        beneficiario.setDocumentos(documentos);
        return beneficiario;
    }

    public BeneficiarioResponse toResponse(Beneficiario b) {
        List<DocumentoDTO> docs = b.getDocumentos().stream().map(d -> new DocumentoDTO(d.getTipoDocumento(),
                d.getDescricao())).toList();
        return new BeneficiarioResponse(b.getId(), b.getName(), b.getTelefone(), b.getDataDeNascimento(), docs);

    }


}
