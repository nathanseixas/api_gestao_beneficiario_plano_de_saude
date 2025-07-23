package api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter//lombok
@Setter//lombok
public class Documento extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoDocumento;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "beneficiario_id") // faz a ligacao entre as duas tabelas
    private Beneficiario beneficiario;



}
