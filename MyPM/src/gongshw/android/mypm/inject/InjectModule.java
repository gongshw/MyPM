package gongshw.android.mypm.inject;

import gongshw.android.mypm.service.LocationService;
import gongshw.android.mypm.service.PmService;
import gongshw.android.mypm.service.impl.LocationServiceImpl;
import gongshw.android.mypm.service.impl.PmServiveImpl;

import com.google.inject.Binder;
import com.google.inject.Module;


public class InjectModule implements Module {

	@Override
	public void configure(Binder binder) {
		binder.bind(PmService.class).to(PmServiveImpl.class);
		binder.bind(LocationService.class).to(LocationServiceImpl.class);
	}

}
