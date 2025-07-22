package api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude.repository;

import api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

//todos metodos de crud estao aqui ex: findById, finALl
public interface DocumentoRepository extends JpaRepository<Documento, Long> {
}
