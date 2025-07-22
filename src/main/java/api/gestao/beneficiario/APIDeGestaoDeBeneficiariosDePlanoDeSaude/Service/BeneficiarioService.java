
// REGRA DE NEGOCIOS
// AQUI SAO IMPORTADAS OS MAPPERS E OS REPOSITORYS
package api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude.Service;

import api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude.mapper.BeneficiarioMapper;
import api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude.repository.BeneficiarioRepository;
import org.springframework.stereotype.Service;

@Service
public class BeneficiarioService {

    private final BeneficiarioMapper beneficiarioMapper;
    private final BeneficiarioRepository beneficiarioRepository;


    public BeneficiarioService(BeneficiarioMapper beneficiarioMapper, BeneficiarioRepository beneficiarioRepository) {
        this.beneficiarioMapper = beneficiarioMapper;
        this.beneficiarioRepository = beneficiarioRepository;
    }

    // METODO "SALVAR"

}
