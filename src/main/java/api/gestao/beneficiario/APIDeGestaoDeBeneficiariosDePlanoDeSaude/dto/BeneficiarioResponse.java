package api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude.dto;

import java.time.LocalDate;
import java.util.List;

public record BeneficiarioResponse(Long id, String nome, String telefone, LocalDate dataDeNascimento, List<DocumentoDTO> documentos) {
}
