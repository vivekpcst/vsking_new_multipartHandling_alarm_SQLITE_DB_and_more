package dzo.com.barcodescanner;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.provider.MediaStore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.app.Activity.RESULT_OK;

public class BarCodeScanner extends Fragment {
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_bar_code_scanner, container, false);
        Button btBarReader=view.findViewById(R.id.btBarReader);
        WebView web=view.findViewById(R.id.web);
        web.getSettings().setJavaScriptEnabled(true);
//        web.loadData("<iframe src=\"https://calendar.google.com/calendar/embed?showTitle=0&amp;showPrint=0&amp;showTabs=0&amp;showCalendars=0&amp;mode=AGENDA&amp;height=450&amp;wkst=2&amp;hl=en&amp;bgcolor=%23ffffff&amp;src=en.indian%23holiday%40group.v.calendar.google.com&amp;color=%2329527A&amp;ctz=Asia%2FKolkata\" style=\"border-width:0\" width=\"300\" height=\"450\" frameborder=\"0\" scrolling=\"no\"></iframe>"
//        ,"html/text",null);
        String url="<iframe src=\"https://calendar.google.com/calendar/embed?showTitle=0&amp;showPrint=0&amp;showTabs=0&amp;showCalendars=0&amp;mode=AGENDA&amp;height=450&amp;wkst=2&amp;hl=en&amp;bgcolor=%23ffffff&amp;src=en.indian%23holiday%40group.v.calendar.google.com&amp;color=%2329527A&amp;ctz=Asia%2FKolkata\" style=\"border-width:0\" width=\"800\" height=\"450\" frameborder=\"0\" scrolling=\"no\"></iframe>";
        web.loadDataWithBaseURL("",url,
                "html/text","UTF-8","");
        btBarReader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                readBarCode();
            }
        });
        Button btFestList=view.findViewById(R.id.btFestList);
        btFestList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),Fest.class));
            }
        });



//        return super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    private void readBarCode() {
        Intent brIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(brIntent,99);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==99){
            if (resultCode==RESULT_OK){
                Bitmap photo=(Bitmap)data.getExtras().get("data");
                ImageView imageView=view.findViewById(R.id.imageView);
                imageView.setImageBitmap(photo);
                barCodeReco(photo);
            }
        }

    }

    private void barCodeReco(Bitmap photo) {
        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(photo);
        FirebaseVisionBarcodeDetector detector = FirebaseVision.getInstance()
                .getVisionBarcodeDetector();
        Task<List<FirebaseVisionBarcode>> result = detector.detectInImage(image)
                .addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionBarcode>>() {
                    @Override
                    public void onSuccess(List<FirebaseVisionBarcode> barcodes) {
                        for (FirebaseVisionBarcode barcode: barcodes) {
                            Rect bounds = barcode.getBoundingBox();
                            Point[] corners = barcode.getCornerPoints();

                            String rawValue = barcode.getRawValue();
                            TextView textView=view.findViewById(R.id.barDetails);
                            textView.setText(rawValue);
                            int valueType = barcode.getValueType();
                            // See API reference for complete list of supported types
                            switch (valueType) {
                                case FirebaseVisionBarcode.TYPE_WIFI:
                                    String ssid = barcode.getWifi().getSsid();
                                    String password = barcode.getWifi().getPassword();
                                    int type = barcode.getWifi().getEncryptionType();
                                    break;
                                case FirebaseVisionBarcode.TYPE_URL:
                                    String title = barcode.getUrl().getTitle();
                                    String url = barcode.getUrl().getUrl();
                                    break;
                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Opps ! Something went wrong.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
