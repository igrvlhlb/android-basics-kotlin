package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private var score = 0
    private var currentWordCount = 0
    lateinit private var _currentScrambledWord: String
    val currentScrambledWord: String
        get() = _currentScrambledWord
    private val usedWordIndexes: MutableSet<Int> = mutableSetOf()
    private lateinit var currentWord: String

    init {
        Log.d("GameFragment", "GameViewModel created!")
        getNextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment", "GameViewModel destroyed!")
    }

    fun getNextWord() {
        val len = allWordsList.size
        var nextIdx = (0..len).random()
        while (usedWordIndexes.contains(nextIdx)) {
            nextIdx = (0..len).random()
        }
        usedWordIndexes.add(nextIdx)
        currentWord = allWordsList[nextIdx]
        val tmpWord = currentWord.toCharArray()
        tmpWord.shuffle()
        while (String(tmpWord).equals(currentWord, false)) {
            tmpWord.shuffle()
        }
        _currentScrambledWord = String(tmpWord)
        currentWordCount += 1
    }

    /*
    * Returns true if the current word count is less than MAX_NO_OF_WORDS.
    * Updates the next word.
    */
    fun nextWord(): Boolean {
        return if (currentWordCount < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }
}