package example.mvclab.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import example.mvclab.entity.Word;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class JsonWordListService implements WordListService {
    private List<Word> wordList;

    @Override
    public List<Word> getWords() {
        return wordList;
    }

    @PostConstruct
    private void initDictionaryData() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            Word[] wordArray = mapper.readValue(Paths.get("E:\\Spring 2022\\Distributed Java 2\\mvcAssignment\\words" +
                    ".json").toFile(), Word[].class);
            wordList = Arrays.asList(wordArray);
        } catch (IOException e) {
            e.printStackTrace();
            wordList = new ArrayList<>(0);
        }
    }

    public String getWord(String word) {
        for (Word item : wordList) {
            if (item.getTerm().equalsIgnoreCase(word)) {
                return item.getDefinition();
            }
        }
        return "no definition found";
    }

    public Word getDefinition(Word word){
        for (Word item: wordList) {
            if(item.getTerm().equalsIgnoreCase(word.getTerm())) {
                word.setDefinition(item.getDefinition());
                return word;
            }
            word.setDefinition("No definition exist");
        }
        return word;
    }
}
