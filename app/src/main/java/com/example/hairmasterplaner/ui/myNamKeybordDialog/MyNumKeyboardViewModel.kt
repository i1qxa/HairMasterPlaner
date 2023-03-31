package com.example.hairmasterplaner.ui.myNamKeybordDialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hairmasterplaner.ui.printToLog

class MyNumKeyboardViewModel : ViewModel() {

    private var _resultLD = MutableLiveData<String>()
    val resultLD: LiveData<String>
        get() = _resultLD

    private var _shouldFinishWork = MutableLiveData<Int>()
    val shouldFinishWork:LiveData<Int>
    get() = _shouldFinishWork

    private var _errorToastMessage = MutableLiveData<String>()
    val errorToastMessage:LiveData<String>
    get() = _errorToastMessage

    private var successCalculate = true
    private var firstNum:Int? = null
    private var secondNum:Int? = null
    private var sign: Char? = null
    private var result:Int? = null


    fun addNewValue(btnText:String){
        when(btnText){
            "0" -> setupNum("0".toInt())
            "1" -> setupNum("1".toInt())
            "2" -> setupNum("2".toInt())
            "3" -> setupNum("3".toInt())
            "4" -> setupNum("4".toInt())
            "5" -> setupNum("5".toInt())
            "6" -> setupNum("6".toInt())
            "7" -> setupNum("7".toInt())
            "8" -> setupNum("8".toInt())
            "9" -> setupNum("9".toInt())
            "+" -> setupSign('+')
            "-" -> setupSign('-')
            "*" -> setupSign('*')
            "/" -> setupSign('/')
            "=" -> setupSign('=')

        }
    }
    private fun setupNum(num: Int) {
        if (sign == null) {
            firstNum = firstNum?:0 * 10 + num
            firstNum.printToLog()
        } else {
            secondNum = secondNum?:0 * 10 + num
            secondNum.printToLog()
        }
    }

    private fun setupSign(inputSign:Char){
        if (firstNum!=null && secondNum!=null){
            //если пришел знак = , то нужно закрывать калькулятор и возвращаться на экран
            // вызвавший калькулятор
            if (inputSign == '='){
                calculateResult()
                if (successCalculate) finishWork()
            }
            //в противном случае результат записываем в первое число знаку присваиваем
            // пришедшее значение, а второе число зануляем
            else{
                if (successCalculate){
                    firstNum = result
                    secondNum = null
                    sign = inputSign
                }
            }
        }
    }

    private fun calculateResult(){
        if (validateNums()){
            successCalculate = true
            result = when(sign){
                '+' -> firstNum!! + secondNum!!
                '-' -> firstNum!! - secondNum!!
                '*' -> firstNum!! * secondNum!!
                '/' -> calculateDivision()
                else -> {
                    successCalculate = false
                    throw java.lang.RuntimeException("Wrong sign : $sign")
                }
            }
            _resultLD.value = "$firstNum $sign $secondNum = $result"
        }
    }

    private fun calculateDivision():Int{
        return if (secondNum==0){
            successCalculate=false
            setupToastErrorMessage("Ошибка деления на ноль")
            0
        }
        else firstNum!! / secondNum!!
    }

    private fun validateNums():Boolean{
        return firstNum!=null && secondNum!=null
    }

    private fun setupToastErrorMessage(text:String){
        _errorToastMessage.value = text
    }

    private fun finishWork(){
        _shouldFinishWork.value = result?:0
    }
}