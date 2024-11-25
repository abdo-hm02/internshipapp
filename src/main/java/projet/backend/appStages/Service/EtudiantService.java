package projet.backend.appStages.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import projet.backend.appStages.DTO.EtudiantRequestDTO;
import projet.backend.appStages.entity.Etudiant;
import projet.backend.appStages.exception.ElementNotFoundException;
import projet.backend.appStages.repository.EtudiantRepository;
import projet.backend.appStages.repository.StageRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;


@Transactional
@Service
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private PromoService promoService;
    @Autowired
    private StageRepository stageRepository;

    /*public Etudiant convertDTOToEtudiant(EtudiantRequestDTO dto) throws ElementNotFoundException {
        Etudiant etudiant = new Etudiant();
        etudiant.setAdresse(dto.getAdresse());
        etudiant.setNom(dto.getNom());
        etudiant.setPrenom(dto.getPrenom());
        etudiant.setSexe(dto.getSexe());
        etudiant.setVille(dto.getVille());
        etudiant.setNumTel(dto.getNumTel());
        etudiant.setCodePostal(dto.getCodePostal());
        etudiant.setQualite(dto.getQualite());
        etudiant.setDdn(dto.getDdn());
        if (dto.getIdPromo()!=0) etudiant.setPromo(promoService.getPromoById(dto.getIdPromo()));
        return etudiant;
    } */
    
    public Etudiant convertDTOToEtudiant(EtudiantRequestDTO dto) throws ElementNotFoundException {
        Etudiant etudiant = new Etudiant();
        etudiant.setAdresse(dto.getAdresse());
        etudiant.setNom(dto.getNom());
        etudiant.setPrenom(dto.getPrenom());
        etudiant.setSexe(dto.getSexe());
        etudiant.setVille(dto.getVille());
        etudiant.setNumTel(dto.getNumTel());
        etudiant.setCodePostal(dto.getCodePostal());
        etudiant.setQualite(dto.getQualite());
        etudiant.setDdn(dto.getDdn());
        etudiant.setMention(dto.getMention());
        if (dto.getIdPromo()!=0) etudiant.setPromo(promoService.getPromoById(dto.getIdPromo()));
        return etudiant;
    }


    public Etudiant addEtudiant(EtudiantRequestDTO e) throws ElementNotFoundException {
        
        Etudiant etudiant = convertDTOToEtudiant(e);
        String rang = String.valueOf(promoService.getNombreEtudiants(e.getIdPromo())+1);
        String promo = String.valueOf(e.getIdPromo());
        String idString =promo.substring(promo.length()-2) + StringUtils.leftPad(rang,3,'0');
        Long id = Long.parseLong(idString);
        etudiant.setId(id);
        return etudiantRepository.save(etudiant);
    }
    
    

    public Etudiant getEtudiantById(Long id) throws ElementNotFoundException {
        Optional<Etudiant> option = etudiantRepository.findById(id);
        if(option.isPresent())   return option.get();
        else throw new ElementNotFoundException("L'étudiant n'existe pas");
    }

    public List<Etudiant> getEtudiants(){
        return etudiantRepository.findAll();
    }

    public Etudiant getEtudiantByName(String nom, String prenom){
        return etudiantRepository.findByNomAndPrenom(nom, prenom).get();
    }

    public void deleteEtudiant(long id) throws ElementNotFoundException {
        if (etudiantRepository.existsById(id)){
            stageRepository.deleteByEtudiantId(id);
            etudiantRepository.deleteById(id);
        }else{
            throw new ElementNotFoundException("L'étudiant n'existe pas.");
        }
    }

    public List<Etudiant> getEtudiantsByPromo(long id){
        return etudiantRepository.findByPromoId(id);
    }

    public void assignEtudiantToPromo(long idE, long idP) throws ElementNotFoundException {
        Etudiant e = getEtudiantById(idE);
        e.setPromo(promoService.getPromoById(idP));
        etudiantRepository.save(e);
    }

    public Etudiant updateEtudiant(long id, EtudiantRequestDTO e) throws ElementNotFoundException {
        Etudiant etudiant = getEtudiantById(id);
        etudiant.setNom(e.getNom());
        etudiant.setPrenom(e.getPrenom());
        etudiant.setCodePostal(e.getCodePostal());
        etudiant.setNumTel(e.getNumTel());
        etudiant.setMention(e.getMention());
        etudiant.setAdresse(e.getAdresse());
        etudiant.setSexe(e.getSexe());
        etudiant.setDdn(e.getDdn());
        etudiant.setVille(e.getVille());
        if(e.getIdPromo()!=0) etudiant.setPromo(promoService.getPromoById(e.getIdPromo()));
        return etudiantRepository.save(etudiant);
    }



}
