

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

    // Essa função pega o que veio de fora (tipo JSON da requisição HTTP)
    // e transforma num objeto que o sistema entende (Beneficiario).
    public Beneficiario toEntity(BeneficiarioRequest request) {
        Beneficiario beneficiario = new Beneficiario();

        // Pega o nome, telefone e data de nascimento do pedido e coloca no cliente
        beneficiario.setName(request.name());
        beneficiario.setTelefone(request.telefone());
        beneficiario.setDataDeNascimento(request.dataDeNascimento());

        // Agora pega os documentos do pedido (CPF, RG, etc),
        // e transforma cada um num objeto Documento do sistema
        List<Documento> documentos = request.documentos().stream().map(d -> {
            Documento doc = new Documento();
            doc.setTipoDocumento(d.tipoDocumento());
            doc.setDescricao(d.descricao());
            doc.setBeneficiario(beneficiario);
            return doc;
        }).toList();

        // Coloca os documentos dentro do beneficiário
        beneficiario.setDocumentos(documentos);

        // Retorna o beneficiário montadinho
        return beneficiario;
    }

    // Essa função faz o contrário: ela pega um beneficiário do sistema
    // e transforma em algo que pode ser mostrado pra fora (tipo um JSON bonitinho)
    public BeneficiarioResponse toResponse(Beneficiario b) {
        List<DocumentoDTO> docs = b.getDocumentos().stream().map(d -> new DocumentoDTO(d.getTipoDocumento(),
                d.getDescricao())).toList();

        // Cria um BeneficiarioResponse com todos os dados e os documentos convertidos
        return new BeneficiarioResponse(
                b.getId(),
                b.getName(),
                b.getTelefone(),
                b.getDataDeNascimento(),
                docs
        );

    }


}
