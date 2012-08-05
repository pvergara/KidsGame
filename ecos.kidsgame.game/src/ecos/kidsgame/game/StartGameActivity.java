package ecos.kidsgame.game;

import java.util.List;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.inject.Inject;

import ecos.framework.Binding.BindingAction;
import ecos.framework.Binding.BindingManager;
import ecos.framework.Speech.SpeechEngine;
import ecos.kidsgame.appdomain.Game.Dto.SilabaDto;
import ecos.kidsgame.game.viewmodel.StartGameViewModel;

public class StartGameActivity extends RoboActivity {

	@InjectView(R.id.main_iniciar)
	Button mIniciar;
	@Inject
	StartGameViewModel mStartGameActivityViewModel;
	@Inject
	SpeechEngine mSpeechEngine;

	@InjectView(R.id.radio1)
	RadioButton mGrupoSilaba1;
	@InjectView(R.id.radio2)
	RadioButton mGrupoSilaba2;
	@InjectView(R.id.radio3)
	RadioButton mGrupoSilaba3;
	@InjectView(R.id.radio4)
	RadioButton mGrupoSilaba4;
	@InjectView(R.id.radio5)
	RadioButton mGrupoSilaba5;
	@InjectView(R.id.radio6)
	RadioButton mGrupoSilaba6;
	@InjectView(R.id.radio7)
	RadioButton mGrupoSilaba7;
	@InjectView(R.id.radio8)
	RadioButton mGrupoSilaba8;
	@InjectView(R.id.radio9)
	RadioButton mGrupoSilaba9;
	@InjectView(R.id.radio10)
	RadioButton mGrupoSilaba10;
	@InjectView(R.id.radio11)
	RadioButton mGrupoSilaba11;
	@InjectView(R.id.radio12)
	RadioButton mGrupoSilaba12;
	@InjectView(R.id.radio13)
	RadioButton mGrupoSilaba13;
	@InjectView(R.id.radio14)
	RadioButton mGrupoSilaba14;
	@InjectView(R.id.radio15)
	RadioButton mGrupoSilaba15;
	@InjectView(R.id.radio16)
	RadioButton mGrupoSilaba16;
	@InjectView(R.id.radio17)
	RadioButton mGrupoSilaba17;
	@InjectView(R.id.radio18)
	RadioButton mGrupoSilaba18;
	@InjectView(R.id.radio19)
	RadioButton mGrupoSilaba19;
	@InjectView(R.id.radio20)
	RadioButton mGrupoSilaba20;
	@InjectView(R.id.radio21)
	RadioButton mGrupoSilaba21;
	@InjectView(R.id.radio22)
	RadioButton mGrupoSilaba22;

	private OnClickListener mIniciarOnClick = new OnClickListener() {

		public void onClick(View v) {
			siguienteActividad();
		}
	};
	private BindingManager mBidingManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mIniciar.setOnClickListener(mIniciarOnClick);

		mBidingManager = mStartGameActivityViewModel.getBindingManager();
		mBidingManager.manageOnChangeFor("grupoSilaba1Tag", baGrupoSilaba1Tag, this);
		mBidingManager.manageOnChangeFor("grupoSilaba2Tag", baGrupoSilaba2Tag, this);
		mBidingManager.manageOnChangeFor("grupoSilaba3Tag", baGrupoSilaba3Tag, this);
		mBidingManager.manageOnChangeFor("grupoSilaba4Tag", baGrupoSilaba4Tag, this);
		mBidingManager.manageOnChangeFor("grupoSilaba5Tag", baGrupoSilaba5Tag, this);
		mBidingManager.manageOnChangeFor("grupoSilaba6Tag", baGrupoSilaba6Tag, this);
		mBidingManager.manageOnChangeFor("grupoSilaba7Tag", baGrupoSilaba7Tag, this);
		mBidingManager.manageOnChangeFor("grupoSilaba8Tag", baGrupoSilaba8Tag, this);
		mBidingManager.manageOnChangeFor("grupoSilaba9Tag", baGrupoSilaba9Tag, this);
		mBidingManager.manageOnChangeFor("grupoSilaba10Tag", baGrupoSilaba10Tag, this);
		mBidingManager.manageOnChangeFor("grupoSilaba11Tag", baGrupoSilaba11Tag, this);
		mBidingManager.manageOnChangeFor("grupoSilaba12Tag", baGrupoSilaba12Tag, this);
		mBidingManager.manageOnChangeFor("grupoSilaba13Tag", baGrupoSilaba13Tag, this);
		mBidingManager.manageOnChangeFor("grupoSilaba14Tag", baGrupoSilaba14Tag, this);
		mBidingManager.manageOnChangeFor("grupoSilaba15Tag", baGrupoSilaba15Tag, this);
		mBidingManager.manageOnChangeFor("grupoSilaba16Tag", baGrupoSilaba16Tag, this);
		mBidingManager.manageOnChangeFor("grupoSilaba17Tag", baGrupoSilaba17Tag, this);
		mBidingManager.manageOnChangeFor("grupoSilaba18Tag", baGrupoSilaba18Tag, this);
		mBidingManager.manageOnChangeFor("grupoSilaba19Tag", baGrupoSilaba19Tag, this);
		mBidingManager.manageOnChangeFor("grupoSilaba20Tag", baGrupoSilaba20Tag, this);
		mBidingManager.manageOnChangeFor("grupoSilaba21Tag", baGrupoSilaba21Tag, this);
		mBidingManager.manageOnChangeFor("grupoSilaba22Tag", baGrupoSilaba22Tag, this);

		mStartGameActivityViewModel.init();
	}

	protected void actualizarTagDe(TextView mGrupoSilaba, Object sourceElementValue) {
		if(!(sourceElementValue instanceof List<?>)) {
			return;
		}
		@SuppressWarnings("unchecked")
		List<SilabaDto> agrupacionSilabasDto = (List<SilabaDto>) sourceElementValue;
		mGrupoSilaba.setTag(agrupacionSilabasDto);
		mGrupoSilaba.setText(arrayToString2(agrupacionSilabasDto, ","));
	}

	private String arrayToString2(List<SilabaDto> list, String separator) {
		StringBuffer result = new StringBuffer();
		if (list.size() > 0) {
			result.append(list.get(0).getTexto());
			for (int i = 1; i < list.size(); i++) {
				result.append(separator);
				result.append(list.get(i).getTexto());
			}
		}
		return result.toString();
	}


	private BindingAction baGrupoSilaba1Tag = new BindingAction() {

		public void fireAction(Object sourceElementValue) {
			actualizarTagDe(mGrupoSilaba1, sourceElementValue);
		}
	};

	private BindingAction baGrupoSilaba2Tag = new BindingAction() {

		public void fireAction(Object sourceElementValue) {
			actualizarTagDe(mGrupoSilaba2, sourceElementValue);
		}
	};

	private BindingAction baGrupoSilaba3Tag = new BindingAction() {

		public void fireAction(Object sourceElementValue) {
			actualizarTagDe(mGrupoSilaba3, sourceElementValue);
		}
	};

	private BindingAction baGrupoSilaba4Tag = new BindingAction() {

		public void fireAction(Object sourceElementValue) {
			actualizarTagDe(mGrupoSilaba4, sourceElementValue);
		}
	};

	private BindingAction baGrupoSilaba5Tag = new BindingAction() {

		public void fireAction(Object sourceElementValue) {
			actualizarTagDe(mGrupoSilaba5, sourceElementValue);
		}
	};


	private BindingAction baGrupoSilaba6Tag = new BindingAction() {

		public void fireAction(Object sourceElementValue) {
			actualizarTagDe(mGrupoSilaba6, sourceElementValue);
		}
	};

	private BindingAction baGrupoSilaba7Tag = new BindingAction() {

		public void fireAction(Object sourceElementValue) {
			actualizarTagDe(mGrupoSilaba7, sourceElementValue);
		}
	};

	private BindingAction baGrupoSilaba8Tag = new BindingAction() {

		public void fireAction(Object sourceElementValue) {
			actualizarTagDe(mGrupoSilaba8, sourceElementValue);
		}
	};

	private BindingAction baGrupoSilaba9Tag = new BindingAction() {

		public void fireAction(Object sourceElementValue) {
			actualizarTagDe(mGrupoSilaba9, sourceElementValue);
		}
	};

	private BindingAction baGrupoSilaba10Tag = new BindingAction() {

		public void fireAction(Object sourceElementValue) {
			actualizarTagDe(mGrupoSilaba10, sourceElementValue);
		}
	};
	
	private BindingAction baGrupoSilaba11Tag = new BindingAction() {

		public void fireAction(Object sourceElementValue) {
			actualizarTagDe(mGrupoSilaba11, sourceElementValue);
		}
	};
	
	private BindingAction baGrupoSilaba12Tag = new BindingAction() {

		public void fireAction(Object sourceElementValue) {
			actualizarTagDe(mGrupoSilaba12, sourceElementValue);
		}
	};
	
	private BindingAction baGrupoSilaba13Tag = new BindingAction() {

		public void fireAction(Object sourceElementValue) {
			actualizarTagDe(mGrupoSilaba13, sourceElementValue);
		}
	};
	
	private BindingAction baGrupoSilaba14Tag = new BindingAction() {

		public void fireAction(Object sourceElementValue) {
			actualizarTagDe(mGrupoSilaba14, sourceElementValue);
		}
	};
	
	private BindingAction baGrupoSilaba15Tag = new BindingAction() {

		public void fireAction(Object sourceElementValue) {
			actualizarTagDe(mGrupoSilaba15, sourceElementValue);
		}
	};
	
	private BindingAction baGrupoSilaba16Tag = new BindingAction() {

		public void fireAction(Object sourceElementValue) {
			actualizarTagDe(mGrupoSilaba16, sourceElementValue);
		}
	};
	
	private BindingAction baGrupoSilaba17Tag = new BindingAction() {

		public void fireAction(Object sourceElementValue) {
			actualizarTagDe(mGrupoSilaba17, sourceElementValue);
		}
	};
	
	private BindingAction baGrupoSilaba18Tag = new BindingAction() {

		public void fireAction(Object sourceElementValue) {
			actualizarTagDe(mGrupoSilaba18, sourceElementValue);
		}
	};
	
	private BindingAction baGrupoSilaba19Tag = new BindingAction() {

		public void fireAction(Object sourceElementValue) {
			actualizarTagDe(mGrupoSilaba19, sourceElementValue);
		}
	};
	
	private BindingAction baGrupoSilaba20Tag = new BindingAction() {

		public void fireAction(Object sourceElementValue) {
			actualizarTagDe(mGrupoSilaba20, sourceElementValue);
		}
	};
	
	private BindingAction baGrupoSilaba21Tag = new BindingAction() {

		public void fireAction(Object sourceElementValue) {
			actualizarTagDe(mGrupoSilaba21, sourceElementValue);
		}
	};
	
	private BindingAction baGrupoSilaba22Tag = new BindingAction() {

		public void fireAction(Object sourceElementValue) {
			actualizarTagDe(mGrupoSilaba22, sourceElementValue);
		}
	};
	
	public void siguienteActividad() {
		mStartGameActivityViewModel.iniciarSiguienteActividad(StartGameActivity.this);
	}

	
	public void seleccionarGrupo(View grupoSilaba){
		@SuppressWarnings("unchecked")
		List<SilabaDto> tag = (List<SilabaDto>) grupoSilaba.getTag();
		List<SilabaDto> agrupacionSilabasDto = tag;
		mStartGameActivityViewModel.seleccionarGrupo(agrupacionSilabasDto);
	}

}