package by.mrkip.apps.weatherarchive.activities;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.IOException;
import java.util.List;

import by.mrkip.apps.weatherarchive.App;
import by.mrkip.apps.weatherarchive.R;
import by.mrkip.apps.weatherarchive.adapters.WeatherCardAdapter;
import by.mrkip.apps.weatherarchive.jsonParsers.PastWeatherListPresenter;
import by.mrkip.apps.weatherarchive.location.LocationActivity;
import by.mrkip.apps.weatherarchive.model.WeatherCard;
import by.mrkip.apps.weatherarchive.utils.BackendQueryBuilder;
import by.mrkip.apps.weatherarchive.utils.OutAppActions;
import by.mrkip.libs.http.HttpClient;

public class CityLastMonthWeatherActivity extends AppCompatActivity {
	public static final String EXTRA_CITY_LAN = "city_lan";
	public static final String EXTRA_CITY_LON = "city_lon";
	public static final int BACK_DAYS_END = 30;
	public static final int BACK_DAYS_START = 1;

	private List<WeatherCard> cardsList;
	private RecyclerView recyclerView;

	@SuppressWarnings("WrongConstant")
	private BackendQueryBuilder systemService = (BackendQueryBuilder) App.getAppContext().getSystemService(App.BACKEND_QUERY_BUILDER);

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_city_last_month_weather);

		initActivityElements();
		initRecyclerView();

		Intent intent = getIntent();

		//TODO constnats
		String city_lan = intent.getStringExtra(EXTRA_CITY_LAN);
		String city_lon = intent.getStringExtra(EXTRA_CITY_LON);
		//TODO: BackendQueryBuilder() - app singltone[+]

		new MyTask().execute(systemService.getPastDaysWeatherQuery(city_lan, city_lon, BACK_DAYS_START, BACK_DAYS_END));

	}

	private void initActivityElements() {
		Toolbar toolbar = (Toolbar) findViewById(R.id.alone_toolbar);
		setSupportActionBar(toolbar);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
	}


	private void initRecyclerView() {
		recyclerView = (RecyclerView) findViewById(R.id.sca_view_recycle);

		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(new WeatherCardAdapter(cardsList, 0));

	}


	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}
		if (id == R.id.action_get_location) {
			Intent intent = new Intent(this, LocationActivity.class);
			startActivity(intent);
			return true;
		} else if (id == R.id.action_mail) {
			//noinspection WrongConstant
			((OutAppActions) App.getAppContext().getSystemService(App.OUT_APP_ACTIONS)).gotoMail(null);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	class MyTask extends AsyncTask<String, Integer, List<WeatherCard>> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

		}

		@Override
		protected List<WeatherCard> doInBackground(String... args) {
			//noinspection WrongConstant
			HttpClient httpClient = (HttpClient) App.getAppContext().getSystemService(App.HTTP_CLIENT);
			try {
				List<WeatherCard> testL;
				testL = httpClient.getResult(args[0], new PastWeatherListPresenter());
				return testL;

			} catch (IOException e) {
				Log.e(this.toString(), this.toString() + "|IOException :", e);
				return null;

			} catch (Exception e) {
				Log.e(this.toString(), this.toString() + "|Exception:", e);
				return null;

			}

		}

		@Override
		protected void onProgressUpdate(Integer... vd) {
			super.onProgressUpdate(vd);

		}

		@Override
		protected void onPostExecute(List<WeatherCard> result) {
			if (result != null) {
				cardsList = result;
				((WeatherCardAdapter) recyclerView.getAdapter()).addItems(result);
			} else {
				Snackbar.make(recyclerView, getString(R.string.sca_nodata), Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();
			}
		}

	}

}
