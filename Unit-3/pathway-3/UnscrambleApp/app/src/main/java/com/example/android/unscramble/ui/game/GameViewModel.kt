package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private var _score = 0
    val score: Int
        get() = _score
    private var _currentWordCount = 0
    val currentWordCount: Int
        get() = _currentWordCount
    private val _currentScrambledWord =  MutableLiveData<String>()
    val currentScrambledWord: LiveData<String>
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
        _currentScrambledWord.value = String(tmpWord)
        _currentWordCount += 1
    }

    /*
    * Returns true if the current word count is less than MAX_NO_OF_WORDS.
    * Updates the next word.
    */
    fun nextWord(): Boolean {
        return if (_currentWordCount < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

    private fun increaseScore() {
        _score += SCORE_INCREASE
    }

    fun isUserWordCorrect(playerWord: String): Boolean {
        if (playerWord.equals(currentWord, true)) {
            increaseScore()
            return true
        }
        return false
    }

    /**
     * Reinitializes the game data to reinitialize the game
     */
    fun reinitializeData() {
        _score = 0
        _currentWordCount = 0
        usedWordIndexes.clear()
        getNextWord()
    }
}