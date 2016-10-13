package com.example.raza.networkrequestmanagment.network.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.raza.networkrequestmanagment.NetworkRequestManagment;
import com.example.raza.networkrequestmanagment.network.activity.TagActivity;
import com.example.raza.networkrequestmanagment.network.activity.TagActivityClean;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ImageUtils {
    private static ImageUtils instance = null;
    private static final String[] CS_IMAGE_OPTIONS = new String[]{"Take Picture", "Gallery"};
    public static final int TAKE_IMAGE_CAMERA = 1;
    public static final int UPLOAD_IMAGE_GALLERY = 2;

    private ImageUtils() {
    }

    public static ImageUtils getInstance() {
        if (instance == null) {
            instance = new ImageUtils();
        }
        return instance;
    }

    public void showDialogPicture(byte[] bsImage, String title) {
        try {
            showDialogPicture(getImage(bsImage), title);
        } catch (Exception e) {
            Toast.makeText(NetworkRequestManagment.mContext, "Unable to Load Image", Toast.LENGTH_LONG).show();
        }
    }

    public void showDialogPicture(Bitmap image, String title) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(NetworkRequestManagment.mContext);
            ImageView imageView = new ImageView(NetworkRequestManagment.mContext);
            if (title != null && title.length() > 0) {
                builder.setTitle(title);
            }
            builder.setView(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageBitmap(image);
            AlertDialog alertDialog = builder.create();
            alertDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND, WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
            alertDialog.show();
        } catch (Exception e) {
            Toast.makeText(NetworkRequestManagment.mContext, "Error displaying image", Toast.LENGTH_LONG).show();
        }
    }

    public void showDialogPictureOptions(DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(NetworkRequestManagment.mContext);
        builder.setTitle("Select Option");
        builder.setItems(CS_IMAGE_OPTIONS, listener);
        builder.show();
    }

    public byte[] setImageOnActivityResult(int requestCode, int resultCode, Intent data, ImageView imageView, String pictureLocation) throws Exception {
        byte[] bsPicture = null;
        if (resultCode == Activity.RESULT_OK && data != null) {
            Uri uriSelectedImage;
            String picturePath;
            Bitmap bitmapPictureTaken;
            Bitmap bitmapResized;
            BitmapFactory.Options bitmapFactoryOptions;
            switch (requestCode) {
                case UPLOAD_IMAGE_GALLERY:
                    uriSelectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = NetworkRequestManagment.mContext.getContentResolver().query(uriSelectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    picturePath = cursor.getString(columnIndex);
                    cursor.close();
                    bitmapFactoryOptions = new BitmapFactory.Options();
                    bitmapFactoryOptions.inSampleSize = 2;
                    bitmapPictureTaken = BitmapFactory.decodeFile(picturePath, bitmapFactoryOptions);
                    bitmapResized = Bitmap.createScaledBitmap(bitmapPictureTaken, bitmapPictureTaken.getWidth() * 2, bitmapPictureTaken.getHeight() * 2, false);
                    bsPicture = getBytes(bitmapResized, picturePath, pictureLocation);
                    break;
                case TAKE_IMAGE_CAMERA:
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    bitmapResized = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() * 2, bitmap.getHeight() * 2, true);
                    File photo = null;
                    FileOutputStream stream;
                    String fileName = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                    fileName = fileName.replaceAll(" ", "");
                    fileName = fileName.replaceAll(":", "");
                    fileName = fileName.replaceAll(",", "");
                    fileName = fileName + ".jpg";
                    photo = new File(android.os.Environment.getExternalStorageDirectory(), fileName);
                    stream = new FileOutputStream(photo);
                    bitmapResized.compress(CompressFormat.JPEG, 100, stream);
                    uriSelectedImage = Uri.fromFile(photo);
                    picturePath = uriSelectedImage.getPath();
                    bitmapPictureTaken = (Bitmap) data.getExtras().get("data");
                    bitmapFactoryOptions = new BitmapFactory.Options();
                    bitmapFactoryOptions.inSampleSize = 2;
                    bsPicture = getBytes(bitmapResized, picturePath, pictureLocation);
                    break;
            }
            if (bsPicture != null) {
                bitmapPictureTaken = getImage(bsPicture);
                imageView.setImageBitmap(bitmapPictureTaken);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        }
        return bsPicture;
    }

    public byte[] setImageOnActivityResult(int requestCode, int resultCode, Intent data, ImageView imageView) throws Exception {
        byte[] bsPicture = null;
        if (resultCode == Activity.RESULT_OK && data != null) {
            Uri uriSelectedImage;
            String picturePath;
            Bitmap bitmapPictureTaken;
            Bitmap bitmapResized;
            BitmapFactory.Options bitmapFactoryOptions;
            switch (requestCode) {
                case UPLOAD_IMAGE_GALLERY:
                    uriSelectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = NetworkRequestManagment.mContext.getContentResolver().query(uriSelectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    picturePath = cursor.getString(columnIndex);
                    cursor.close();
                    bitmapFactoryOptions = new BitmapFactory.Options();
                    bitmapFactoryOptions.inSampleSize = 2;
                    bitmapPictureTaken = BitmapFactory.decodeFile(picturePath, bitmapFactoryOptions);
                    bitmapResized = Bitmap.createScaledBitmap(bitmapPictureTaken, bitmapPictureTaken.getWidth() * 2, bitmapPictureTaken.getHeight() * 2, false);
                    bsPicture = getBytes(bitmapResized, picturePath);
                    break;
                case TAKE_IMAGE_CAMERA:
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    bitmapResized = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() * 2, bitmap.getHeight() * 2, true);
                    File photo = null;
                    FileOutputStream stream;
                    String fileName = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                    fileName = fileName.replaceAll(" ", "");
                    fileName = fileName.replaceAll(":", "");
                    fileName = fileName.replaceAll(",", "");
                    fileName = fileName + ".jpg";
                    photo = new File(android.os.Environment.getExternalStorageDirectory(), fileName);
                    stream = new FileOutputStream(photo);
                    bitmapResized.compress(CompressFormat.JPEG, 100, stream);
                    uriSelectedImage = Uri.fromFile(photo);
                    picturePath = uriSelectedImage.getPath();
                    bitmapPictureTaken = (Bitmap) data.getExtras().get("data");
                    bitmapFactoryOptions = new BitmapFactory.Options();
                    bitmapFactoryOptions.inSampleSize = 2;
                    // bitmapPictureTaken = BitmapFactory.decodeFile(picturePath, bitmapFactoryOptions);
                    // Bitmap bitmapResized = Bitmap.createScaledBitmap(bitmapPictureTaken, bitmapPictureTaken.getWidth()/2, bitmapPictureTaken.getHeight()/2, false);
                    TagActivity.filePath = picturePath;
                    TagActivityClean.filePath = picturePath;
                    bsPicture = getBytes(bitmapResized, picturePath);
                    break;
            }
            if (bsPicture != null) {
                bitmapPictureTaken = getImage(bsPicture);
                imageView.setImageBitmap(bitmapPictureTaken);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        }
        return bsPicture;
    }

    public byte[] setImageOnActivityResult(int requestCode, int resultCode, Intent data) throws Exception {
        byte[] bsPicture = null;
        if (resultCode == Activity.RESULT_OK && data != null) {
            Uri uriSelectedImage;
            String picturePath;
            Bitmap bitmapPictureTaken;
            Bitmap bitmapResized;
            BitmapFactory.Options bitmapFactoryOptions;
            switch (requestCode) {
                case UPLOAD_IMAGE_GALLERY:
                    uriSelectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = NetworkRequestManagment.mContext.getContentResolver().query(uriSelectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    picturePath = cursor.getString(columnIndex);
                    cursor.close();
                    bitmapFactoryOptions = new BitmapFactory.Options();
                    bitmapFactoryOptions.inSampleSize = 2;
                    bitmapPictureTaken = BitmapFactory.decodeFile(picturePath, bitmapFactoryOptions);
                    bitmapResized = Bitmap.createScaledBitmap(bitmapPictureTaken, bitmapPictureTaken.getWidth(), bitmapPictureTaken.getHeight(), false);
                    bsPicture = getSimpleBytes(bitmapResized);
                    break;
                case TAKE_IMAGE_CAMERA:
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    bitmapResized = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);
                    bsPicture = getSimpleBytes(bitmapResized);
                    break;
            }
        }
        return bsPicture;
    }

    public byte[] getBytes(Bitmap bitmap) {
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() * 2, bitmap.getHeight() * 2, true);//createBitmap(bitmap, 0, 0, 500, 700, matrix, true);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy  HH:mm");
        String dateTime = sdf.format(Calendar.getInstance().getTime()); // reading local time in the system

        Canvas cs = new Canvas(resizedBitmap);
        Paint tPaint = new Paint();
        tPaint.setTextSize(27);
        tPaint.setColor(Color.RED);
        tPaint.setStyle(Style.FILL);
        cs.drawText(dateTime, resizedBitmap.getWidth() - 200, resizedBitmap.getHeight() - 10, tPaint);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        resizedBitmap.compress(CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }

    public byte[] getBytes(Bitmap resizedBitmap, String picturePath, String pictureLocation) {
        File file = new File(picturePath);
//    	bitmap=BitmapFactory.decodeFile(picturePath);
        Log.v("bitmap res", resizedBitmap.getWidth() + " " + resizedBitmap.getHeight());
        //Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);//createBitmap(bitmap, 0, 0, 500, 700, matrix, true);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy  HH:mm");
        // String dateTime = sdf.format(Calendar.getUsage().getTime()); // reading local time in the system
        String dateTime = sdf.format(file.lastModified());
        Canvas cs = new Canvas(resizedBitmap);
        Paint tPaint = new Paint();
        if (resizedBitmap.getWidth() > resizedBitmap.getHeight()) {
            tPaint.setTextSize(27);
        } else {
            tPaint.setTextSize(20);
        }
        tPaint.setColor(Color.RED);
        tPaint.setStyle(Style.FILL);
        cs.drawText(dateTime, 10, resizedBitmap.getHeight() - 60, tPaint);
        if (pictureLocation != null && !pictureLocation.equals(""))
            cs.drawText("Location: " + pictureLocation, 10, resizedBitmap.getHeight() - 30, tPaint);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        resizedBitmap.compress(CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }

    public byte[] getBytes(Bitmap resizedBitmap, String picturePath) {
        File file = new File(picturePath);
//    	bitmap=BitmapFactory.decodeFile(picturePath);
        Log.v("bitmap res", resizedBitmap.getWidth() + " " + resizedBitmap.getHeight());
        //Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);//createBitmap(bitmap, 0, 0, 500, 700, matrix, true);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy  HH:mm");
        // String dateTime = sdf.format(Calendar.getUsage().getTime()); // reading local time in the system
        String dateTime = sdf.format(file.lastModified());
        Canvas cs = new Canvas(resizedBitmap);
        Paint tPaint = new Paint();
        if (resizedBitmap.getWidth() > resizedBitmap.getHeight()) {
            tPaint.setTextSize(27);
        } else {
            tPaint.setTextSize(20);
        }
        tPaint.setColor(Color.RED);
        tPaint.setStyle(Style.FILL);
        cs.drawText(dateTime, 10, resizedBitmap.getHeight() - 60, tPaint);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        resizedBitmap.compress(CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }


    public byte[] getSimpleBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }

    public Bitmap getImage(byte[] image) throws Exception {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}