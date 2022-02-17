package example.mvclab.service;

import example.mvclab.entity.Word;

import java.util.List;

public interface WordListService {
    List<Word> getWords();

    String getWord(String word);
    Word getDefinition(Word word);
}

