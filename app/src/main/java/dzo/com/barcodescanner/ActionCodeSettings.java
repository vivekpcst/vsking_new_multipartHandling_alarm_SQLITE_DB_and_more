package dzo.com.barcodescanner;

import android.os.Parcel;
import android.os.Parcelable;

public class ActionCodeSettings extends Object implements Parcelable {
    protected ActionCodeSettings(Parcel in) {
    }

    public static final Creator<ActionCodeSettings> CREATOR = new Creator<ActionCodeSettings>() {
        @Override
        public ActionCodeSettings createFromParcel(Parcel in) {
            return new ActionCodeSettings(in);
        }

        @Override
        public ActionCodeSettings[] newArray(int size) {
            return new ActionCodeSettings[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
