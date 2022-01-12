package com.toastlib;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SimpleToast {

    // Toast Duration
    public static final int TOAST_LONG_DURATION = 1;
    public static final int TOAST_SHORT_DURATION = 2;

    // Text Size
    public static final int TEXT_EXTRA_SMALL_SIZE = 9;
    public static final int TEXT_SMALL_SIZE = 3;
    public static final int TEXT_NORMAL_SIZE = 4;
    public static final int TEXT_BIG_SIZE = 5;

    // Gravity
    public static final int TOAST_BOTTOM_POSITION = 6;
    public static final int TOAST_CENTER_POSITION = 7;
    public static final int TOAST_TOP_POSITION = 8;

    //************************************
    //        Main library class
    //************************************
    public SimpleToast(Create create){

        // Taking context and getting Layout Inflater Service
        Context context = create.context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // Inflate custom toast layout and ViewGroup is null
        View layout = inflater.inflate(R.layout.custom_toast, null);

        // Setup views: TextView and ImageView
        TextView textView = (TextView) layout.findViewById(R.id.textView);
        ImageView imageView = (ImageView) layout.findViewById(R.id.imageView);

        // Set some TextView values (text, color, size, style)
        textView.setText(create.text);
        textView.setTextColor(create.textColor);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, create.textSize);
        if (create.textBold) textView.setTypeface(null, Typeface.BOLD);
        else textView.setTypeface(null, Typeface.NORMAL);

        // Set some ImageView values
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setAdjustViewBounds(true);
        imageView.setImageResource(create.imageValue);

        // Set some shape values
        GradientDrawable drawable;

        // If gradient then apply linear option
        if (create.isGradient){
             drawable = new GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM,
                    new int[]{create.gradientColorStart,
                            create.gradientColorStart,
                            create.gradientColorEnd,
                            create.gradientColorEnd}
            );
            drawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        }else{

            //If not, apply plain color
            drawable = new GradientDrawable();
            drawable.setColor(create.backgroundColor);
        }

        drawable.setShape(create.shape);
        drawable.setCornerRadii(new float[]{create.radiusTopLeft,create.radiusTopLeft,
                create.radiusTopRight,create.radiusTopRight,create.radiusBottomRight,create.radiusBottomRight,
                create.radiusBottomLeft,create.radiusBottomLeft});

        drawable.setStroke(create.borderWidth, create.borderColor);

        // Include the shape into layout and show it
        layout.setBackground(drawable);
        Toast toast = new Toast(create.context);
        toast.setView(layout);
        toast.setDuration(create.toastLength);
        toast.setGravity(create.toastPosition, 0, 100);
        toast.show();
    }


    public static void success(Context context, String text){
        new Create(context, text)
                .setBackgroundColor(context.getResources().getColor(R.color.colorGreen))
                .setBorderColor(context.getResources().getColor(R.color.colorGreenDark))
                .setImageValue(R.mipmap.ic_launcher)
                .setTextSize(TEXT_NORMAL_SIZE)
                .setAllRadius(100)
                .show();
    }

    public static class Create{

        private Context context;

        private String text;
        private int textColor;
        private int textSize;
        private boolean textBold;

        private int imageValue;

        private int backgroundColor;
        private int borderColor;
        private int shape;
        private int borderWidth;

        private int toastPosition;
        private int toastLength;

        private boolean isGradient;
        private int gradientColorStart;
        private int gradientColorEnd;

        private int radiusTopLeft;
        private int radiusBottomLeft;
        private int radiusTopRight;
        private int radiusBottomRight;

        public Create(Context context, String text){
            this.context = context;
            this.text = text;

            // Text values
            setTextSize(TEXT_NORMAL_SIZE);
            textBold = false;

            // Default Colors
            this.backgroundColor = context.getResources().getColor(R.color.colorGrey);
            this.borderColor = context.getResources().getColor(R.color.colorBlack);
            this.textColor = context.getResources().getColor(R.color.colorWhite);
            this.gradientColorStart = context.getResources().getColor(R.color.colorBlack);
            this.gradientColorEnd = context.getResources().getColor(R.color.colorGrey);

            // Image
            this.imageValue = 0;

            // Default shape
            this.shape = GradientDrawable.RECTANGLE;

            // Radius
            radiusTopLeft = 0;
            radiusBottomLeft = 0;
            radiusTopRight = 0;
            radiusBottomRight = 0;

            // Gradient option
            isGradient = false;

            // Border Width
            borderWidth = 8;

            // Toast values
            toastPosition = Gravity.BOTTOM;
            toastLength = Toast.LENGTH_SHORT;

        }

        // Setter section

        public Create setText(String text) {
            this.text = text;
            return this;
        }

        public Create setImageValue(int imageValue) {
            this.imageValue = imageValue;
            return this;
        }

        public Create setBackgroundColor(int backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public Create setBorderColor(int borderColor) {
            this.borderColor = borderColor;
            return this;
        }

        public Create setTextColor(int textColor) {
            this.textColor = textColor;
            return this;
        }

        public Create setIsGradient(boolean gradient) {
            isGradient = gradient;
            return this;
        }

        public Create setGradientColorStart(int gradientColorStart) {
            this.gradientColorStart = gradientColorStart;
            return this;
        }

        public Create setGradientColorEnd(int gradientColorEnd) {
            this.gradientColorEnd = gradientColorEnd;
            return this;
        }

        public Create setTextSize(int textSize) {
            switch (textSize){
                case TEXT_EXTRA_SMALL_SIZE:
                    this.textSize = 8;
                    break;
                case TEXT_SMALL_SIZE:
                    this.textSize = 12;
                    break;
                case TEXT_BIG_SIZE:
                    this.textSize = 24;
                    break;
                case TEXT_NORMAL_SIZE:
                    this.textSize = 16;
                    break;
                default:
                    this.textSize = 20;
                    break;
            }
            return this;
        }

        public Create setTextBold(boolean textBold) {
            this.textBold = textBold;
            return this;
        }

        public Create setShape(int shape) {
            this.shape = shape;
            return this;
        }

        public Create setToastPosition(int toastGravity) {
            switch (toastGravity){
                case TOAST_CENTER_POSITION:
                    this.toastPosition = Gravity.CENTER;
                    break;
                case TOAST_TOP_POSITION:
                    this.toastPosition = Gravity.TOP;
                    break;
                default:
                    this.toastPosition = Gravity.BOTTOM;
                    break;
            }
            return this;
        }

        public Create setBorderWidth(int borderWidth) {
            this.borderWidth = borderWidth;
            return this;
        }

        public Create setToastLength(int toastLength) {
            switch (toastLength){
                case TOAST_LONG_DURATION:
                    this.toastLength = Toast.LENGTH_LONG;
                    break;
                default:
                    this.toastLength = Toast.LENGTH_SHORT;
                    break;
            }
            return this;
        }

        public Create setRadiusTopLeft(int radiusTopLeft) {
            this.radiusTopLeft = radiusTopLeft;
            return this;
        }

        public Create setRadiusBottomLeft(int radiusBottomLeft) {
            this.radiusBottomLeft = radiusBottomLeft;
            return this;
        }

        public Create setRadiusTopRight(int radiusTopRight) {
            this.radiusTopRight = radiusTopRight;
            return this;
        }

        public Create setRadiusBottomRight(int radiusBottomRight) {
            this.radiusBottomRight = radiusBottomRight;
            return this;
        }

        public Create setAllRadius(int radius){
            this.radiusTopLeft = radius;
            this.radiusTopRight = radius;
            this.radiusBottomLeft = radius;
            this.radiusBottomRight = radius;
            return this;
        }

        // Show Awesome Toast in Screen
        public void show(){
            new SimpleToast(this);
        }

    }
}