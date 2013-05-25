package gongshw.android.mypm.factory;

import gongshw.android.mypm.inject.InjectModule;
import gongshw.android.mypm.service.LocationService;
import gongshw.android.mypm.service.PmService;

import android.content.Context;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class ServiceFactory {

	private ServiceFactory() {
		InjectModule mo = new InjectModule();
		Injector in = Guice.createInjector(mo);
		in.injectMembers(this);
	}

	private static ServiceFactory _FACTORY;

	private PmService _pmService;
	private LocationService _lService;

	public static ServiceFactory getInstance() {
		if (_FACTORY == null) {
			_FACTORY = new ServiceFactory();
		}
		return _FACTORY;
	}

	public PmService getPmService() {
		return _pmService;
	}
	
	public LocationService getLocationService(Context context) {
		_lService.setContext(context);
		return _lService;
	}
	

	@Inject
	void injectPmService(PmService service) {
		this._pmService = service;
	}
	
	@Inject
	void injectLocationService(LocationService service){
		this._lService = service;
	}

}
