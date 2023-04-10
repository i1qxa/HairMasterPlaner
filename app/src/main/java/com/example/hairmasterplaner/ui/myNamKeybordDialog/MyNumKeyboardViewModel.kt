package com.example.hairmasterplaner.ui.myNamKeybordDialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

const val PLUS_SIGN = 11
const val MINUS_SIGN = 12
const val MULTI_SIGN = 13
const val DIVISION_SIGN = 14
const val EQUAL_SIGN = 15

class MyNumKeyboardViewModel : ViewModel() {

    private var _resultLD = MutableLiveData<String>()
    val resultLD: LiveData<String>
        get() = _resultLD

    private var _shouldFinishWork = MutableLiveData<Int>()
    val shouldFinishWork: LiveData<Int>
        get() = _shouldFinishWork

    private var _errorToastMessage = MutableLiveData<String>()
    val errorToastMessage: LiveData<String>
        get() = _errorToastMessage

    private var successCalculate = true
    private var firstNum: Int? = null
    private var secondNum: Int? = null
    private var sign: Int? = null
    private var result: Int? = null

    fun setupFirstDigit(num:Int){
        firstNum = num
        printExpressionToLD()
    }
    fun addNewValue(btnValue: Int) {
        when (btnValue) {
            in 0.. 9 -> setupNum(btnValue)
            in PLUS_SIGN .. DIVISION_SIGN -> {
                setupSign(btnValue)
            }
            else -> {
                calculateResult()
                finishWork()
            }
        }
    }

    fun deleteLastChar() {
        if (result == null) {
            if (secondNum!=null) {
                secondNum = secondNum!! / 10
                if (secondNum == 0) secondNum = null
            }
            else if (firstNum != null && sign != null) sign = null
            else if (firstNum != null) {
                firstNum = firstNum!! / 10
                if (firstNum == 0) firstNum = null
            }
        }
        else{
            clearAll()
        }
        printExpressionToLD()
    }

    fun clearAll() {
        firstNum = null
        secondNum = null
        sign = null
        result = null
        printExpressionToLD()
    }

    private fun setupNum(num: Int) {
        if (sign == null) {
            firstNum = (firstNum ?: 0) * 10 + num
            printExpressionToLD()
        } else {
            secondNum = (secondNum ?: 0) * 10 + num
            printExpressionToLD()
        }
    }

    private fun setupSign(inputSign: Int) {
        if (firstNum!=null && secondNum!=null){
            calculateResult()
            firstNum = result
            sign = inputSign
            secondNum = null
            result = null
            printExpressionToLD()
        }
        else if (firstNum!=null){
            sign = inputSign
            printExpressionToLD()
        }
        else{
            setupToastErrorMessage("Сначала необходимо указать хотябы одно число")
        }
    }

    private fun calculateResult() {
        if (firstNum!=null && secondNum!=null) {
            result = when (sign) {
                PLUS_SIGN -> firstNum!! + secondNum!!
                MINUS_SIGN -> firstNum!! - secondNum!!
                MULTI_SIGN -> firstNum!! * secondNum!!
                DIVISION_SIGN -> calculateDivision()
                else -> {
                    throw java.lang.RuntimeException("Wrong sign : $sign")
                }
            }
        }else if (firstNum!=null){
            result = firstNum
        }
        else{
            setupToastErrorMessage("Сначала необходимо указать хотябы одно число")
        }
    }

    private fun calculateDivision(): Int {
        return if (secondNum == 0) {
            successCalculate = false
            setupToastErrorMessage("Ошибка деления на ноль")
            0
        } else firstNum!! / secondNum!!
    }

    private fun setupToastErrorMessage(text: String) {
        _errorToastMessage.value = text
    }

    private fun finishWork() {
        _shouldFinishWork.value = result ?: 0
    }

    private fun signIntToString(sign: Int): String {
        return when (sign) {
            PLUS_SIGN -> "+"
            MINUS_SIGN -> "-"
            MULTI_SIGN -> "*"
            DIVISION_SIGN -> "/"
            else -> "="
        }
    }

    private fun printExpressionToLD() {
        val leftSide = StringBuilder()
        with(leftSide) {
            append(firstNum ?: "")
            if (sign != null) append(signIntToString(sign!!))
            append(secondNum ?: "")
            if (result != null) {
                append(" = ")
                append(result)
            }
        }
        _resultLD.value = leftSide.toString()
    }

}