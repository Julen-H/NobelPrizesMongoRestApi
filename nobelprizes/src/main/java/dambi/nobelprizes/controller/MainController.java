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
 * Klase hau Rest Api-aren kontroladorea da. Klase honetan Rest Api-aren
 * Endpoint-ak eta beraien portaerak
 * zehazten dira. Aplikazio honek sei Endpoint ezberdin ditu web eskaera beraz
 * gain. Aplikazioaren funtzionalitatea
 * CRUD baten moduan funtzionatzen du rest zerbitzuak datu basean datuak
 * gehituz, ezabatuz, aktualizatuz eta
 * bistaratuz. Endpoint bakoitza eskaera ezberdin bat egiteaz arduratzen da.
 * 
 * @author Julen Herrero
 * 
 */
@RestController
@RequestMapping(path = "/nobel")
public class MainController {

    @Autowired
    private PrizeRepository prizeRepository;

    /**
     * Funtzio honek GET request-ak egiteko mapping-a ezartzen du. Endpoint-a
     * zehazten du eta endpoint-aren
     * funtzionamendua ezartzen du. Gure sarien errepositorioko sari guztiak
     * bilatzen ditu eta JSON formatuan
     * bueltatzen ditu automatikoki (XML formatuan ere bueltatu dezake findAll()
     * funtzioak). Kasu honetan
     * Prize klaseko Iterable-ak bueltatzen ditu.
     * 
     * @return Iterable<Prize> Prize motako Iterable bat, honek sari guztiak bere gain hartuz
     */
    @GetMapping(path = "/sariguztiak")
    public @ResponseBody Iterable<Prize> getPrizes() {
        return prizeRepository.findAll();
    }

    /**
     * Funtzio honek GET request-ak egiteko Endpoint zehatza ezartzen du. Funtzioak
     * String motako
     * parametro bat jasotzen du kasu honetan sariaren identifikadorea izanik.
     * Parametro horrekin
     * sarien errepositorioan identifikadore hori duen saria topatzen du eta
     * bueltatzen du JSON formatuan.
     * Eskaera hau interesgarria da soilik sari bat topatzea nahi baduzu, hala ere
     * kontutan izan behar da
     * bere identifikadorea ezagutzea beharrezkoa dela.
     * 
     * @param String _id Sari zehatzaren identifikadorea String formatuan
     * @return Prize Pasatako identifikadorearekin bat etortzen duen saria
     */
    @GetMapping(path = "/sariabilatu")
    public @ResponseBody Prize findPrize(@RequestParam String _id) {
        return prizeRepository.findById(_id);
    }

    /**
     * Funtzio honek GET request-ak egiteko Endpoint bat zehazten du. Funtzio honek
     * String motako bi
     * parametro jasotzen ditu, lehen String-a sariaren urtea izanik eta bigarrena
     * sariaren kategoria
     * izanik. Funtzioak errepositorioetako funtzioei deitzen die eta sarien
     * kolekzioan pasatako urtea
     * eta kategoriarekin koinziditzen duen Saria bueltatzen du JSON formatuan. Bi
     * parametro ezberdin
     * jasotzeko Criteria klaseko query bat erabiltzen du.
     * 
     * @param year Saria banatu zen urtea String formatuan
     * @param category Sariaren kategoria edo disziplina String formatuan
     * @return Prize Pasatako urte eta kategoriarekin bat etortzen duen saria 
     */
    @GetMapping(path = "/bilatuKategoriaUrteka")
    public @ResponseBody Prize findPrizeByCatYear(@RequestParam String year, @RequestParam String category) {
        return prizeRepository.findByCategoryYear(year, category);
    }

    /**
     * Funtzio honek DELETE request-ak egiteko Endpoint-a zehazten du. Funtzioak
     * String motako
     * parametro bat jasotzen du, parametro hau ezabatu nahi den sariaren
     * identifikadorea izango
     * litzateke. Zuzenean errepositorioan pasatako identifikadorea duen saria
     * ezabatzen du, horretarako
     * delete funtzioari deitzen dio, honek bueltan ezabatutako sarien zenbaketa
     * bueltatuz. Beti bat izango da,
     * ez bada bat jasotzen errore bat egon dela suposatuko luke.
     * 
     * @param String _id Sari zehatzaren identifikadorea String formatuan
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

    /**
     * Funtzio honek DELETE motatako request-ak egiteko Endpoint bat mapeatzen du.
     * Funtzio honek String
     * motako bi parametro jasotzen ditu. Delete motatako request-ek mongo
     * errepositorioan aurkitzen diren
     * delete funtzioei deitzen die eta hauek ezabatu diren sari kopurua bueltatzen
     * dute behin hura ezabatzean.
     * Beti bat ezabatuko dute eta ez bada bat jasotzen errore bat gertatu dela
     * jakin daiteke.
     * 
     * @param year Saria banatu zen urtea String formatuan
     * @param category Sariaren kategoria edo disziplina String formatuan
     * @return String Saria era egokian edo desegokian gorde delaren mezu bat String formatuan
     * 
     */
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

    /**
     * Funtzio honek POST motako request-ak egiteko Endpoint-a mapeatzen du. Funtzio
     * honen bitartez datu basean
     * sari berri bat gehitu daiteke. Horretarako Prize motako sari bat sortzen da
     * konstruktorean zehaztutako
     * parametroekin, behin saria sortuta sarien errepositoriaren gordetzen da.
     * 
     * Prize klaseko objektuek hiru atributu dituzte, alde batetik sariaren urtea
     * eta kategoria biak String
     * motatakoak izanik, eta bestetik Laureates objektuen lista bat.
     * 
     * @param year Saria banatu zen urtea String formatuan
     * @param category Sariaren kategoria edo disziplina String formatuan
     * @param laureates Sari hori jaso zuten pertsonen lista, Laureate motako objektuen lista bat izanik
     * @return String Saria era egokian gehitu delaren mezu bat String formatuan
     */
    @PostMapping(value = "/sariberria")
    public @ResponseBody String sariBerriaGehitu(@RequestParam String year, @RequestParam String category,
            @RequestBody List<Laureate> laureates) {

        Prize p = new Prize();
        p.setYear(year);
        p.setCategory(category);
        p.setLaureates(laureates);
        prizeRepository.save(p);
        return "Nobel Saria era egokian gehitu da";
    }

    /**
     * Funtzio honek PUT motatako request-ak egiteko Endpoint-a mapeatzen du.
     * Funtzio honen bitartez sari
     * zehatz bat eguneratu daiteke. Hau lortzeko POST-ean egiten den moduan
     * sariaren parametroak bidaltzen
     * dira request-aren bitartez. Hala ere, kasu honetan sariaren identifikadorea
     * ere eskatzen da. Lehenik
     * aldatu nahi den saria topatzen da identifikadorearen bitartez eta geroago
     * pasatu diren parametroak
     * aldatzen dira setter-en bitartez. Sarituen kasuan, lehen zuen sarituei saritu
     * berriak gehitzen dira.
     * 
     * @param _id Sari zehatzaren identifikadorea String formatuan
     * @param year Saria banatu zen urtea String formatuan
     * @param category Sariaren kategoria edo disziplina String formatuan
     * @param laureate Sari hori jaso zuen beste pertsona bat, sarituetara gehitzeko asmoa izanik
     * @return ResponseEntity<Prize> 
     */
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
