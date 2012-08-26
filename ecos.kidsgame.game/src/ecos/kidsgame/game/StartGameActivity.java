package ecos.kidsgame.game;

import java.util.List;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.inject.Inject;

import ecos.framework.Binding.BindingAction;
import ecos.framework.Binding.BindingManager;
import ecos.kidsgame.appdomain.Game.Dto.AgrupacionDto;
import ecos.kidsgame.appdomain.Game.Dto.SilabaDto;
import ecos.kidsgame.game.viewmodel.StartGameViewModel;

public class StartGameActivity extends RoboActivity {

	@InjectView(R.id.main_iniciar)
	Button mIniciar;

	@Inject
	StartGameViewModel mStartGameActivityViewModel;

	@InjectView(R.id.agrupacionSilabas)
	RadioGroup mAgrupacionSilabas;

	private BindingManager mBidingManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mBidingManager = mStartGameActivityViewModel.getBindingManager();
		mBidingManager.manageOnChangeFor("agrupacionesTag", baAgrupacionesTag, this);

		mStartGameActivityViewModel.init();
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

	private BindingAction baAgrupacionesTag = new BindingAction() {

		public void fireAction(Object sourceElementValue) {
			@SuppressWarnings("unchecked")
			List<AgrupacionDto> agrupacion = (List<AgrupacionDto>) sourceElementValue;
			mAgrupacionSilabas.setTag(agrupacion);
			mAgrupacionSilabas.removeAllViews();
			anhadirRadioButtonA(mAgrupacionSilabas, agrupacion.size(), StartGameActivity.this);
			int i = 0;
			for (AgrupacionDto grupoDeDatosDelJuego : agrupacion) {
				establecerTagEnViewDelGrupo(mAgrupacionSilabas, grupoDeDatosDelJuego, i);
				establecerTextEnViewDelGrupo(mAgrupacionSilabas, grupoDeDatosDelJuego, i);
				establecerOnClickListener(mAgrupacionSilabas,(OnClickListener)agrupacionClick,i);
				i++;
			}

		}
	};

	public void primeraPruebaClick(View v) {
		mStartGameActivityViewModel.iniciarPrimeraPrueba(StartGameActivity.this);
	}

	public void terceraPruebaClick(View v) {
		mStartGameActivityViewModel.iniciarTerceraPrueba(StartGameActivity.this);
	}

	protected void establecerOnClickListener(RadioGroup agrupacionSilabas, OnClickListener agrupacionClick, int i) {
		View v = agrupacionSilabas.getChildAt(i);
		v.setOnClickListener(agrupacionClick);
		
	}

	protected void establecerTextEnViewDelGrupo(RadioGroup agrupacionSilabas, AgrupacionDto grupoDeDatosDelJuego, int i) {
		TextView text = (TextView) agrupacionSilabas.getChildAt(i);
		text.setText(arrayToString2(grupoDeDatosDelJuego.silabasDto, ","));

	}

	protected OnClickListener agrupacionClick = new OnClickListener() {
		
		public void onClick(View v) {
			mStartGameActivityViewModel.seleccionarGrupo((AgrupacionDto) v.getTag());
			
		}
	};

	protected void establecerTagEnViewDelGrupo(RadioGroup agrupacionSilabas, AgrupacionDto grupoDeSilabas, int i) {
		View view = agrupacionSilabas.getChildAt(i);
		view.setTag(grupoDeSilabas);
	}

	protected void anhadirRadioButtonA(RadioGroup agrupacionSilabas, int size, Context context) {
		for (int i = 0; i < size; i++) {
			agrupacionSilabas.addView(new RadioButton(context));
		}

	}

	public void segundaPruebaClick(View v) {
		mStartGameActivityViewModel.iniciarSegundaPrueba(StartGameActivity.this);
	}

	public void explicacionClick(View v) {
		mStartGameActivityViewModel.explicar();
	}

	public void seleccionarGrupo(View grupoSilaba) {
		AgrupacionDto tag = (AgrupacionDto) grupoSilaba.getTag();
		AgrupacionDto agrupacionSilabasDto = tag;
		mStartGameActivityViewModel.seleccionarGrupo(agrupacionSilabasDto);
	}

}