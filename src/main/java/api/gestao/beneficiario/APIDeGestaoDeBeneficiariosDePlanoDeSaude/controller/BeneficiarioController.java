package api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude.controller;

import api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude.Service.BeneficiarioService;
import api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude.dto.BeneficiarioRequest;
import api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude.dto.BeneficiarioResponse;
import api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude.dto.DocumentoDTO;
import api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude.model.Beneficiario;
import api.gestao.beneficiario.APIDeGestaoDeBeneficiariosDePlanoDeSaude.repository.BeneficiarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beneficiario")
public class BeneficiarioController {

    private final BeneficiarioService beneficiarioService;

    public BeneficiarioController(BeneficiarioService beneficiarioService) {
        this.beneficiarioService = beneficiarioService;
    }

    @PostMapping
    public ResponseEntity<BeneficiarioResponse> criarBeneficiario(@RequestBody BeneficiarioRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(beneficiarioService.salvar(request));
    }

   @GetMapping
    public List<BeneficiarioResponse> listarTodos() {
        return beneficiarioService.listarTodos();
   }


   @GetMapping("/{id}/documento")
    public List<DocumentoDTO> listarDocumentos(@PathVariable Long id) {
        return beneficiarioService.listarDocumento(id);
   }

   @PutMapping("/{id}")
   public ResponseEntity<BeneficiarioResponse> atualizar(@PathVariable Long id, @RequestBody BeneficiarioRequest request) {
        return ResponseEntity.ok(beneficiarioService.atualizar(id, request));
   }

   @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        beneficiarioService.remover(id);
        return ResponseEntity.noContent().build();

   }


}
