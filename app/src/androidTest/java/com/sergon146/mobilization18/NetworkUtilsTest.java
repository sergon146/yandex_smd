package com.sergon146.mobilization18;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.test.runner.AndroidJUnit4;

import com.sergon146.mobilization18.util.NetworkUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class NetworkUtilsTest {
    @Test
    public void lostConnectionTest() {
        final Context context = Mockito.mock(Context.class);
        final ConnectivityManager connManager = Mockito.mock(ConnectivityManager.class);
        final NetworkInfo networkInfo = Mockito.mock(NetworkInfo.class);

        when(context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(connManager);
        when(connManager.getActiveNetworkInfo()).thenReturn(networkInfo);
        when(networkInfo.getType()).thenReturn(ConnectivityManager.TYPE_WIFI);

        assertFalse(NetworkUtil.isLostConnection(context));

        when(networkInfo.getType()).thenReturn(ConnectivityManager.TYPE_MOBILE);
        assertFalse(NetworkUtil.isLostConnection(context));
    }
}
