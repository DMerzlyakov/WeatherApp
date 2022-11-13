package com.example.weatherapp.presentation.dialogs

import android.app.AlertDialog
import android.content.Context
import android.view.ContextThemeWrapper
import android.widget.EditText
import com.example.weatherapp.R

// Объект для вывода диалогов
object DialogUtil {

    // Диалог для ввода названия города
    fun searchByName(context: Context, listener: Listener) {
        val builder = AlertDialog.Builder(ContextThemeWrapper(context, R.style.CustomAlertDialog))
        val editText = EditText(context)
        builder.setView(editText)
        val dialog = builder.create()
        dialog.setTitle("Введите название города")
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK") { _, _ ->
            listener.getData(editText.text.toString())
            dialog.dismiss()
        }
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel") { _, _ ->
            dialog.dismiss()
        }

        dialog.show()
    }

    // Диалог для вывода информации о различных ошибках
    fun errorDialog(context: Context, error: String) {
        val dialogBuilder = AlertDialog.Builder(context)

        dialogBuilder.setMessage(error)
            .setCancelable(false)
            .setNegativeButton("Закрыть") { dialog, _ ->
                dialog.cancel()
            }

        val alert = dialogBuilder.create()
        alert.setTitle("Ошибка")
        alert.show()
    }

    // Интерфейс для того, чтобы проще было реализовывать листненеры для диалоговых окон
    interface Listener {
        fun getData(name: String)
    }
}