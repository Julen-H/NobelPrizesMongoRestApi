package dambi.nobelprizes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dambi.nobelprizes.model.Laureate;
import dambi.nobelprizes.model.Prize;
import dambi.nobelprizes.model.PrizeRepository;


/**
 * Klase hau Rest Api-aren kontroladorea da. Klase honetan Rest Api-aren Endpoint-ak eta beraien portaerak 
 * zehazten dira. Aplikazio honek sei Endpoint ezberdin ditu web eskaera beraz gain. 
 * 
 * @author Julen Herrero
 */
@RestController
@RequestMapping(path = "/nobel")
public class MainController {

    @Autowired
    private PrizeRepository prizeRepository;

    
    /** 
     * Funtzio honek GET request-ak egiteko mapping-a ezartzen du. Endpoint-a zehazten du eta endpoint-aren 
     * funtzionamendua ezartzen du. Gure sarien errepositorioko sari guztiak bilatzen ditu eta JSON formatuan
     * bueltatzen ditu automatikoki (XML formatuan ere bueltatu dezake findAll() funtzioak). Kasu honetan
     * Prize klaseko Iterable-ak bueltatzen ditu.
     * 
     * @return Iterable<Prize>
     */
    @GetMapping(path = "/sariguztiak")
    public @ResponseBody Iterable<Prize> getPrizes() {
        return prizeRepository.findAll();
    }

    /**
     * Funtzio honek GET request-ak egiteko Endpoint zehatza ezartzen du. Funtzioak String motako 
     * parametro bat jasotzen du kasu honetan sariaren identifikadorea izanik. Parametro horrekin
     * sarien errepositorioan identifikadore hori duen saria topatzen du eta bueltatzen du JSON formatuan. 
     * Eskaera hau interesgarria da soilik sari bat topatzea nahi baduzu, hala ere kontutan izan behar da
     * bere identifikadorea ezagutzea beharrezkoa dela.
     * 
     * @param String _id
     * @return Prize
     */
    @GetMapping(path = "/sariabilatu")
    public @ResponseBody Prize findPrize(@RequestParam String _id) {
        return prizeRepository.findById(_id);
    }

    /**
     * Funtzio honek DELETE request-ak egiteko Endpoint-a zehazten du. Funtzioak String motako
     * parametro bat jasotzen du, parametro hau ezabatu nahi den sariaren identifikadorea izango
     * litzateke. 
     * 
     * @param String _id
     * @return ResponseEntity<Void>
     */
    @DeleteMapping(path = "/sariaezabatuid/{_id}")
    public ResponseEntity<Void> deletePrize(@PathVariable String _id) {
        try {
            long zenbat = prizeRepository.delete(_id);
            System.out.println("Nobel Saria ezabatua izan da, ezabatutako kopurua: " + zenbat);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("Errore bat gertatu da Nobel Saria ezabatzen, saiatu berriro mesedez...");
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/sariaezabatu")
    public @ResponseBody String deletePrizeUrtea(@RequestParam("year") String year,
            @RequestParam("category") String category) {

        try {
            long ezabatua = prizeRepository.deleteByYear(year, category);

            if (ezabatua > 0) {
                return ("Nobel Saria era egokian ezabatu da");
            } else {
                return ("Errore bat gertatu da Nobel Saria ezabatzen, saiatu berriro mesedez...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Errore bat gertatu da Nobel Saria ezabatzen, saiatu berriro mesedez...";
        }
    }

    @PostMapping(value = "/sariberria/")
    public @ResponseBody String sariBerriaGehitu(@RequestParam String year, @RequestParam String category,
            @RequestBody List<Laureate> laureates) {

        Prize p = new Prize();
        p.setYear(year);
        p.setCategory(category);
        p.setLaureates(laureates);
        prizeRepository.save(p);
        return "Nobel Saria era egokian gehitu da";
    }

    @PutMapping(value = "/sariaeditatu/{_id}")
    public ResponseEntity<Prize> updatePrize(@PathVariable String _id, @RequestParam String year, 
            @RequestParam String category, @RequestBody Laureate laureate) {

        try {
            Prize p = prizeRepository.findById(_id);
            p.setYear(year);
            p.setCategory(category);
            List<Laureate> sarituak = p.getLaureates();
            sarituak.add(laureate);
            prizeRepository.save(p);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

}
