package com.reactnativeautocompleteinput

import android.graphics.Color
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import com.facebook.react.bridge.*
import com.facebook.react.common.MapBuilder
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewDefaults
import com.facebook.react.uimanager.ViewProps
import com.facebook.react.uimanager.annotations.ReactProp
import com.facebook.react.uimanager.events.RCTEventEmitter
import com.facebook.react.views.text.DefaultStyleValuesUtil


class AutoCompleteInputViewManager : SimpleViewManager<AppCompatAutoCompleteTextView>() {
  companion object {
    private const val TAG = "AutocompleteInputView"
  }

  override fun getName() = "AutoCompleteInputView"

  override fun createViewInstance(reactContext: ThemedReactContext): AppCompatAutoCompleteTextView {
    return AppCompatAutoCompleteTextView(reactContext).apply {
      background = null
      val inputType: Int = inputType
      setInputType(inputType and InputType.TYPE_TEXT_FLAG_MULTI_LINE.inv())
//      setReturnKeyType("done")
    }
  }

  override fun addEventEmitters(reactContext: ThemedReactContext, view: AppCompatAutoCompleteTextView) {
    super.addEventEmitters(reactContext, view)
    view.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.d(TAG, "onItemSelected: ${position}, $id")
      }

      override fun onNothingSelected(parent: AdapterView<*>?) {
      }
    }
    view.addTextChangedListener(object : TextWatcher {
      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
      }

      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        Log.d(TAG, "onTextChanged: $s")
        val event: WritableMap = Arguments.createMap()
        event.putString("text", s.toString())
        reactContext.getJSModule(RCTEventEmitter::class.java).receiveEvent(
          view.id,
          "textChanged",
          event)
      }

      override fun afterTextChanged(s: Editable?) {
      }

    })
  }

  override fun getExportedCustomBubblingEventTypeConstants(): Map<String, Any>? {
    return MapBuilder.builder<String, Any>()
      .put(
        "textChanged",
        MapBuilder.of(
          "phasedRegistrationNames",
          MapBuilder.of("bubbled", "onChangeText")))
      .build()
  }

  @ReactProp(name = ViewProps.FONT_SIZE, defaultFloat = ViewDefaults.FONT_SIZE_SP)
  fun setFontSize(view: AppCompatAutoCompleteTextView, fontSize: Float) {
    view.textSize = fontSize
  }

  @ReactProp(name = ViewProps.FONT_STYLE)
  fun setFontStyle(view: AppCompatAutoCompleteTextView, fontStyle: String?) {
//    view.setFontStyle(fontStyle)
  }

  @ReactProp(name = "placeholder")
  fun setPlaceholder(view: AppCompatAutoCompleteTextView, placeholder: String?) {
    view.hint = placeholder
  }

  @ReactProp(name = "placeholderTextColor", customType = "Color")
  fun setPlaceholderTextColor(view: AppCompatAutoCompleteTextView, color: Int?) {
    if (color == null) {
      view.setHintTextColor(DefaultStyleValuesUtil.getDefaultTextColorHint(view.context))
    } else {
      view.setHintTextColor(color)
    }
  }

  @ReactProp(name = "placeholderColor", customType = "Color", defaultInt = Color.BLACK)
  fun setPlaceholderColor(view: AppCompatAutoCompleteTextView, color: Int): Unit {
    view.setHintTextColor(color)
  }

  @ReactProp(name = "textColor", customType = "Color")
  fun setColor(view: AppCompatAutoCompleteTextView, color: Int?) {
    if (color == null) {
      val defaultContextTextColor = DefaultStyleValuesUtil.getDefaultTextColor(view.context)
      if (defaultContextTextColor != null) {
        view.setTextColor(defaultContextTextColor)
      } else {
        val c = view.context
        ReactSoftException.logSoftException(
          TAG,
          IllegalStateException("Could not get default text color from View Context: "
            + if (c != null) c.javaClass.canonicalName else "null"))
      }
    } else {
      view.setTextColor(color)
    }
  }

  @ReactProp(name = "suggestions")
  fun setSuggestions(view: AppCompatAutoCompleteTextView, suggestions: ReadableArray?) {
    val adapter = if (suggestions != null) ArrayAdapter(view.context, android.R.layout.simple_dropdown_item_1line, suggestions.toArrayList().toArray()) else null
    view.setAdapter(adapter)
  }
}
