package ecos.kidsgame.game;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.inject.Inject;

import ecos.framework.Binding.BindingAction;
import ecos.framework.Binding.BindingManager;
import ecos.kidsgame.appdomain.Game.Dto.SilabaDto;
import ecos.kidsgame.game.viewmodel.JuegoViewModel;

public class JuegoActivity extends RoboActivity {
    @InjectView(R.id.juegoBoton1)
    Button mSilaba1;

    @InjectView(R.id.juegoBoton2)
    Button mSilaba2;

    @InjectView(R.id.juegoBoton3)
    Button mSilaba3;

    @InjectView(R.id.juegoBoton4)
    Button mSilaba4;

    @InjectView(R.id.juegoBoton5)
    Button mSilaba5;

    @InjectView(R.id.commonIniciar)
    Button mIniciar;

    @InjectView(R.id.commonSiguiente)
    Button mSiguiente;
    
    @Inject                            
    JuegoViewModel mJuegoViewModel;
    
	private BindingManager mBindingManager;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego);        
        
        mBindingManager =  mJuegoViewModel.getBindingManager();
        
        mSilaba1.setOnClickListener(mSilabasOnClick);        
        mSilaba2.setOnClickListener(mSilabasOnClick);
        mSilaba3.setOnClickListener(mSilabasOnClick);
        mSilaba4.setOnClickListener(mSilabasOnClick);
        mSilaba5.setOnClickListener(mSilabasOnClick);        

        mIniciar.setOnClickListener(mIniciarOnClick);        
        mSiguiente.setOnClickListener(mSiguienteOnClick);        

        mBindingManager.manageOnChangeFor("silaba1Enabled",mBindingActionSilaba1Enabled,this);
		mBindingManager.manageOnChangeFor("silaba2Enabled",mBindingActionSilaba2Enabled,this);
		mBindingManager.manageOnChangeFor("silaba3Enabled",mBindingActionSilaba3Enabled,this);
		mBindingManager.manageOnChangeFor("silaba4Enabled",mBindingActionSilaba4Enabled,this);
		mBindingManager.manageOnChangeFor("silaba5Enabled",mBindingActionSilaba5Enabled,this);

        mBindingManager.manageOnChangeFor("silaba1Tag",mBindingActionSilaba1Tag,this);
		mBindingManager.manageOnChangeFor("silaba2Tag",mBindingActionSilaba2Tag,this);
		mBindingManager.manageOnChangeFor("silaba3Tag",mBindingActionSilaba3Tag,this);
		mBindingManager.manageOnChangeFor("silaba4Tag",mBindingActionSilaba4Tag,this);
		mBindingManager.manageOnChangeFor("silaba5Tag",mBindingActionSilaba5Tag,this);

		mBindingManager.manageOnChangeFor("iniciarEnabled",mBindingActionIniciar,this);
		mBindingManager.manageOnChangeFor("iniciarVisibility",mBindingActionIniciarVisibility,this);
		mBindingManager.manageOnChangeFor("siguienteVisibility",mBindingActionSiguiente,this);
		        
        mJuegoViewModel.init();
    }

	private OnClickListener mSilabasOnClick = new OnClickListener() {
		
		public void onClick(View v) {
			Button silaba = (Button) v; 
			mJuegoViewModel.silabaPulsada(((SilabaDto) silaba.getTag()));
		}
	};

	private OnClickListener mIniciarOnClick = new OnClickListener() {
		
		public void onClick(View v) {
			mJuegoViewModel.iniciarJuego();
		}
	};
	
    private BindingAction mBindingActionSilaba1Enabled = new BindingAction() {
		public void fireAction(Object sourceElementValue) {
			mSilaba1.setEnabled((Boolean) sourceElementValue);
		}
    };
	
    private BindingAction mBindingActionSilaba2Enabled = new BindingAction() {
		public void fireAction(Object sourceElementValue) {
			mSilaba2.setEnabled((Boolean) sourceElementValue);
		}
    };
	
    private BindingAction mBindingActionSilaba3Enabled = new BindingAction() {
		public void fireAction(Object sourceElementValue) {
			mSilaba3.setEnabled((Boolean) sourceElementValue);
		}
    };
	
    private BindingAction mBindingActionSilaba4Enabled = new BindingAction() {
		public void fireAction(Object sourceElementValue) {
			mSilaba4.setEnabled((Boolean) sourceElementValue);
		}
    };
	
    private BindingAction mBindingActionSilaba5Enabled = new BindingAction() {
		public void fireAction(Object sourceElementValue) {
			mSilaba5.setEnabled((Boolean) sourceElementValue);
		}
    };

    private BindingAction mBindingActionSilaba1Tag = new BindingAction() {
		public void fireAction(Object sourceElementValue) {
			mSilaba1.setTag(sourceElementValue);
			mSilaba1.setText(((SilabaDto) sourceElementValue).getTexto());
		}
    };
	
    private BindingAction mBindingActionSilaba2Tag = new BindingAction() {
		public void fireAction(Object sourceElementValue) {
			mSilaba2.setTag(sourceElementValue);
			mSilaba2.setText(((SilabaDto) sourceElementValue).getTexto());
		}
    };
	
    private BindingAction mBindingActionSilaba3Tag = new BindingAction() {
		public void fireAction(Object sourceElementValue) {
			mSilaba3.setTag(sourceElementValue);
			mSilaba3.setText(((SilabaDto) sourceElementValue).getTexto());
		}
    };
	
    private BindingAction mBindingActionSilaba4Tag = new BindingAction() {
		public void fireAction(Object sourceElementValue) {
			mSilaba4.setTag(sourceElementValue);
			mSilaba4.setText(((SilabaDto) sourceElementValue).getTexto());
		}
    };
	
    private BindingAction mBindingActionSilaba5Tag = new BindingAction() {
		public void fireAction(Object sourceElementValue) {
			mSilaba5.setTag(sourceElementValue);
			mSilaba5.setText(((SilabaDto) sourceElementValue).getTexto());
		}
    };
	
    private BindingAction mBindingActionIniciar = new BindingAction() {
		public void fireAction(final Object sourceElementValue) {
			mIniciar.setEnabled((Boolean) sourceElementValue);					
		}
    };
    
	private OnClickListener mSiguienteOnClick = new OnClickListener() {
		
		public void onClick(View v) {
			mJuegoViewModel.siguienteJuego(JuegoActivity.this);
		}
	};

	private BindingAction mBindingActionSiguiente = new BindingAction() {
		
		public void fireAction(Object sourceElementValue) {
			Boolean visible = (Boolean) sourceElementValue;
			mSiguiente.setVisibility(visible?View.VISIBLE:View.GONE);
		}
	};

	private BindingAction mBindingActionIniciarVisibility = new BindingAction() {
		
		public void fireAction(Object sourceElementValue) {
			Boolean visible = (Boolean) sourceElementValue;
			mIniciar.setVisibility(visible?View.VISIBLE:View.GONE);
		}
	};
    
}
