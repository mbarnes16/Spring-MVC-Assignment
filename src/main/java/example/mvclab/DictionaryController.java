package example.mvclab;
import com.fasterxml.jackson.databind.ObjectMapper;
import example.mvclab.entity.Word;
import example.mvclab.service.WordListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Paths;

@Controller
public class DictionaryController {
    private WordListService wordListService;

    @Autowired
    public DictionaryController(WordListService wls) {
        this.wordListService = wls;
    }
//    @PostConstruct
//    private void initDictionaryData() {
//        ObjectMapper mapper = new ObjectMapper();
//
//        try {
//            wordArray = mapper.readValue(Paths.get("E:\\Spring 2022\\Distributed Java 2\\mvcAssignment\\words.json").toFile(), Word[].class);
//        } catch (IOException e) {
//            e.printStackTrace();
//            wordArray = new Word[0];
//        }
//    }

    @RequestMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("words", wordListService.getWords());
        return "index";
    }

    @RequestMapping("/search")
        public String showSearchPage (Model model) {
            model.addAttribute("words", wordListService.getWords());

            Word defaultWord = new Word();
            defaultWord.setTerm("temsis");
            defaultWord.setDefinition("a definition");
            model.addAttribute("word", defaultWord);
            return "search";
        }

    @GetMapping("/{term}")
    public String showDefinition(@PathVariable("term") String term, Model model) {
    model.addAttribute("words", term);
    model.addAttribute("definition", wordListService.getWord(term));
    return "definition";
    }

    @PostMapping("/definition")
    public String showSearchDefinition(@RequestParam(name = "wordSearch") String word, Model model) {
        model.addAttribute("word", word);
        model.addAttribute("definition", wordListService.getWord(word));
        return "definition";
    }
}