package mobi.boilr.boilr.activities;

import mobi.boilr.boilr.services.LocalBinder;
import mobi.boilr.boilr.services.StorageAndControlService;
import mobi.boilr.boilr.utils.Log;
import mobi.boilr.boilr.views.fragments.PriceHitAlarmSettingsFragment;
import mobi.boilr.boilr.views.fragments.PriceVarAlarmSettingsFragment;
import mobi.boilr.libpricealarm.Alarm;
import mobi.boilr.libpricealarm.PriceHitAlarm;
import mobi.boilr.libpricealarm.PriceVarAlarm;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceActivity;

public class AlarmSettingsActivity extends PreferenceActivity {

	private StorageAndControlService mStorageAndControlService;
	private boolean mBound;

	private GetAlarmServiceConnection mStorageAndControlServiceConnection;

	private class GetAlarmServiceConnection implements ServiceConnection {
		private Integer id;

		public GetAlarmServiceConnection(Integer id) {
			this.id = id;
		}

		@Override
		public void onServiceConnected(ComponentName className, IBinder binder) {
			mStorageAndControlService = ((LocalBinder<StorageAndControlService>) binder).getService();
			mBound = true;

			// Callback action performed after the service has been bound
			if(mBound) {

				Alarm alarm = mStorageAndControlService.getAlarm(id);

				Log.d(""+alarm.getExchange());
				Log.d(alarm.getExchange().getName());

				if(alarm instanceof PriceHitAlarm) {
					getFragmentManager().beginTransaction().replace(android.R.id.content, new PriceHitAlarmSettingsFragment(alarm)).commit();
				} else if(alarm instanceof PriceVarAlarm) {
					getFragmentManager().beginTransaction().replace(android.R.id.content, new PriceVarAlarmSettingsFragment(alarm)).commit();
				}
			}
		}

		@Override
		public void onServiceDisconnected(ComponentName className) {
			mBound = false;
		}
	};

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		Bundle bund = getIntent().getExtras();
		Integer id = bund.getInt("id");

		Intent serviceIntent = new Intent(this, StorageAndControlService.class);
		mStorageAndControlServiceConnection = new GetAlarmServiceConnection(id);
		bindService(serviceIntent, mStorageAndControlServiceConnection, Context.BIND_AUTO_CREATE);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unbindService(mStorageAndControlServiceConnection);
	}

	public StorageAndControlService getStorageAndControlService() {
		return mStorageAndControlService;
	}

	public boolean isBound() {
		return mBound;
	}
}
