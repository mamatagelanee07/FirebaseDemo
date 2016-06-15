package com.andyland.firebasedemo.common.util;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Andy.
 * Font used in here is free and downloaded from https://www.fontsquirrel.com/fonts/sansation
 */
public class FontLoader {

    private static FontLoader fontLoader;
    private Context context;

    public Typeface FONT_GOOD_DOG;
    public Typeface FONT_SANSATION_BOLD;
    public Typeface FONT_SANSATION_BOLD_ITALIC;
    public Typeface FONT_SANSATION_ITALIC;
    public Typeface FONT_SANSATION_LIGHT;
    public Typeface FONT_SANSATION_LIGHT_ITALIC;
    public Typeface FONT_SANSATION_REGULAR;

    private FontLoader(Context context) {
        this.context = context;
        init();
    }

    public static FontLoader getInstance(Context context) {
        if (fontLoader == null) {
            fontLoader = new FontLoader(context);
        }
        return fontLoader;
    }

    public void init() {
        FONT_GOOD_DOG = Typeface.createFromAsset(context.getAssets(), Constants.FONT_GOOD_DOG);
        FONT_SANSATION_BOLD = Typeface.createFromAsset(context.getAssets(), Constants.FONT_SANSATION_BOLD);
        FONT_SANSATION_BOLD_ITALIC = Typeface.createFromAsset(context.getAssets(), Constants.FONT_SANSATION_BOLD_ITALIC);
        FONT_SANSATION_ITALIC = Typeface.createFromAsset(context.getAssets(), Constants.FONT_SANSATION_ITALIC);
        FONT_SANSATION_LIGHT = Typeface.createFromAsset(context.getAssets(), Constants.FONT_SANSATION_LIGHT);
        FONT_SANSATION_LIGHT_ITALIC = Typeface.createFromAsset(context.getAssets(), Constants.FONT_SANSATION_LIGHT_ITALIC);
        FONT_SANSATION_REGULAR = Typeface.createFromAsset(context.getAssets(), Constants.FONT_SANSATION_REGULAR);

    }

    public void setTypeFace(final View view, String fontName) {
        try {
            if (view instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) view;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    setTypeFace(child, fontName);
                }
            } else if (view instanceof EditText) {
                if (fontName.equalsIgnoreCase(Constants.FONT_GOOD_DOG))
                    ((EditText) view).setTypeface(fontLoader.FONT_GOOD_DOG);
                else if (fontName.equalsIgnoreCase(Constants.FONT_SANSATION_BOLD))
                    ((EditText) view).setTypeface(fontLoader.FONT_SANSATION_BOLD);
                else if (fontName.equalsIgnoreCase(Constants.FONT_SANSATION_BOLD_ITALIC))
                    ((EditText) view).setTypeface(fontLoader.FONT_SANSATION_BOLD_ITALIC);
                else if (fontName.equalsIgnoreCase(Constants.FONT_SANSATION_ITALIC))
                    ((EditText) view).setTypeface(fontLoader.FONT_SANSATION_ITALIC);
                else if (fontName.equalsIgnoreCase(Constants.FONT_SANSATION_LIGHT))
                    ((EditText) view).setTypeface(fontLoader.FONT_SANSATION_LIGHT);
                else if (fontName.equalsIgnoreCase(Constants.FONT_SANSATION_LIGHT_ITALIC))
                    ((EditText) view).setTypeface(fontLoader.FONT_SANSATION_LIGHT_ITALIC);
                else if (fontName.equalsIgnoreCase(Constants.FONT_SANSATION_REGULAR))
                    ((EditText) view).setTypeface(fontLoader.FONT_SANSATION_REGULAR);

            } else if (view instanceof Button) {

                if (fontName.equalsIgnoreCase(Constants.FONT_GOOD_DOG))
                    ((Button) view).setTypeface(fontLoader.FONT_GOOD_DOG);
                else if (fontName.equalsIgnoreCase(Constants.FONT_SANSATION_BOLD))
                    ((Button) view).setTypeface(fontLoader.FONT_SANSATION_BOLD);
                else if (fontName.equalsIgnoreCase(Constants.FONT_SANSATION_BOLD_ITALIC))
                    ((Button) view).setTypeface(fontLoader.FONT_SANSATION_BOLD_ITALIC);
                else if (fontName.equalsIgnoreCase(Constants.FONT_SANSATION_ITALIC))
                    ((Button) view).setTypeface(fontLoader.FONT_SANSATION_ITALIC);
                else if (fontName.equalsIgnoreCase(Constants.FONT_SANSATION_LIGHT))
                    ((Button) view).setTypeface(fontLoader.FONT_SANSATION_LIGHT);
                else if (fontName.equalsIgnoreCase(Constants.FONT_SANSATION_LIGHT_ITALIC))
                    ((Button) view).setTypeface(fontLoader.FONT_SANSATION_LIGHT_ITALIC);
                else if (fontName.equalsIgnoreCase(Constants.FONT_SANSATION_REGULAR))
                    ((Button) view).setTypeface(fontLoader.FONT_SANSATION_REGULAR);

            } else if (view instanceof TextView) {

                if (fontName.equalsIgnoreCase(Constants.FONT_GOOD_DOG))
                    ((TextView) view).setTypeface(fontLoader.FONT_GOOD_DOG);
                else if (fontName.equalsIgnoreCase(Constants.FONT_SANSATION_BOLD))
                    ((TextView) view).setTypeface(fontLoader.FONT_SANSATION_BOLD);
                else if (fontName.equalsIgnoreCase(Constants.FONT_SANSATION_BOLD_ITALIC))
                    ((TextView) view).setTypeface(fontLoader.FONT_SANSATION_BOLD_ITALIC);
                else if (fontName.equalsIgnoreCase(Constants.FONT_SANSATION_ITALIC))
                    ((TextView) view).setTypeface(fontLoader.FONT_SANSATION_ITALIC);
                else if (fontName.equalsIgnoreCase(Constants.FONT_SANSATION_LIGHT))
                    ((TextView) view).setTypeface(fontLoader.FONT_SANSATION_LIGHT);
                else if (fontName.equalsIgnoreCase(Constants.FONT_SANSATION_LIGHT_ITALIC))
                    ((TextView) view).setTypeface(fontLoader.FONT_SANSATION_LIGHT_ITALIC);
                else if (fontName.equalsIgnoreCase(Constants.FONT_SANSATION_REGULAR))
                    ((TextView) view).setTypeface(fontLoader.FONT_SANSATION_REGULAR);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
