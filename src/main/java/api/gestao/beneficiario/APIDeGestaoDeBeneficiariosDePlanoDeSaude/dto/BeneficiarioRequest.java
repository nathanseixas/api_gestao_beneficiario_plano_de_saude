package api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude.dto;

import java.time.LocalDate;
import java.util.List;

// vai receber todos os atributos da classe, ou seja, todos os atributos da classe Beneficiario
public record BeneficiarioRequest(String name, String telefone, LocalDate dataDeNascimento, List<DocumentoDTO> documentos) {
}
