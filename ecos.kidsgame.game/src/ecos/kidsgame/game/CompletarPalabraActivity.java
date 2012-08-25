package ecos.kidsgame.game;

import java.util.List;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.inject.Inject;

import ecos.framework.Binding.BindingAction;
import ecos.framework.Binding.BindingManager;
import ecos.kidsgame.appdomain.Game.Dto.SilabaDto;
import ecos.kidsgame.game.viewmodel.CompletarPalabrasViewModel;

public class CompletarPalabraActivity extends RoboActivity {
	@Inject
	private CompletarPalabrasViewModel mCompletarPalabraViewModel;

	@InjectView(R.id.palabra)
	private TextView palabraIncompleta;

	@InjectView(R.id.juegoBoton1)
	private Button mSilaba1;

	@InjectView(R.id.juegoBoton2)
	private Button mSilaba2;

	@InjectView(R.id.juegoBoton3)
	private Button mSilaba3;

	@InjectView(R.id.juegoBoton4)
	private Button mSilaba4;

	@InjectView(R.id.juegoBoton5)
	private Button mSilaba5;

	private BindingManager mBindingManager;

	private BindingAction baSilabasActivas = new BindingAction() {
		
		public void fireAction(Object sourceElementValue) {
			mSilaba1.setEnabled((Boolean) sourceElementValue);
			mSilaba2.setEnabled((Boolean) sourceElementValue);
			mSilaba3.setEnabled((Boolean) sourceElementValue);
			mSilaba4.setEnabled((Boolean) sourceElementValue);
			mSilaba5.setEnabled((Boolean) sourceElementValue);
		}
	};

	private BindingAction baSilabas = new BindingAction() {
		
		public void fireAction(Object sourceElementValue) {
			@SuppressWarnings("unchecked")
			List<SilabaDto> silabas = (List<SilabaDto>) sourceElementValue;
			establecerTagDe(mSilaba1,silabas.get(0));
			establecerTagDe(mSilaba2,silabas.get(1));
			establecerTagDe(mSilaba3,silabas.get(2));
			establecerTagDe(mSilaba4,silabas.get(3));
			establecerTagDe(mSilaba5,silabas.get(4));
			
			establecerTextoDe(mSilaba1,silabas.get(0).getTexto());
			establecerTextoDe(mSilaba2,silabas.get(1).getTexto());
			establecerTextoDe(mSilaba3,silabas.get(2).getTexto());
			establecerTextoDe(mSilaba4,silabas.get(3).getTexto());
			establecerTextoDe(mSilaba5,silabas.get(4).getTexto());
		}

	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.completarpalabra);
		
		mBindingManager = mCompletarPalabraViewModel.getBindingManager();
		
		mBindingManager.manageOnChangeFor("Palabra", baPalabra, this);
		mBindingManager.manageOnChangeFor("Silabas", baSilabas, this);
		mBindingManager.manageOnChangeFor("SilabasActivas", baSilabasActivas, this);
		
		mCompletarPalabraViewModel.init();
	}

	protected void establecerTagDe(View view, Object sourceElementValue) {
		view.setTag(sourceElementValue);
	}

	protected void establecerTextoDe(TextView view, Object sourceElementValue) {
		view.setText((String)sourceElementValue);
		
	}
	private BindingAction baPalabra = new BindingAction() {
		
		public void fireAction(Object sourceElementValue) {
			palabraIncompleta.setText((String) sourceElementValue);
		}
	};

	public void explicarClick(View v) {
		mCompletarPalabraViewModel.explicar();
	}

	public void iniciarClick(View v) {
		mCompletarPalabraViewModel.iniciar();
	}

	public void jugarClick(View v){
		mCompletarPalabraViewModel.jugar((SilabaDto) v.getTag());
	}
	
}
