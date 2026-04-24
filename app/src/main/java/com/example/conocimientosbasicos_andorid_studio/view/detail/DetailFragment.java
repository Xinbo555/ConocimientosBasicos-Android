package com.example.conocimientosbasicos_andorid_studio.view.detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.conocimientosbasicos_andorid_studio.R;
import com.example.conocimientosbasicos_andorid_studio.databinding.FragmentDetailBinding;
import com.example.conocimientosbasicos_andorid_studio.domain.entity.Product;
import com.example.conocimientosbasicos_andorid_studio.view.image.ImageLoader;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailFragment extends Fragment  implements DetailView{

    private FragmentDetailBinding binding;

    @Inject
    DetailPresenter presenter;

    @Inject
    ImageLoader imageLoader;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDetailBinding.inflate(inflater, container, false);

        attachPresenter();
        initToolbar();
        initFAB();
        initUi();

        return binding.getRoot();
    }

    private void initFAB() {
        binding.fab.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setTitle("Aviso importante")
                    .setMessage("Vas a realizar una accion")
                    .setPositiveButton("Toast", (dialog, which) -> {
                        Toast.makeText(getContext(), "Esto deberia de llamar a presenter pero mucho boilerplate", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Cancelar", (dialog, which) -> {
                        dialog.dismiss();
                    })
                    .setNeutralButton("Neutral",((dialog, which) -> {
                        Toast.makeText(getContext(), "Texto neutro", Toast.LENGTH_SHORT).show();
                    }));

            builder.create().show();
        });
    }

    private void initToolbar() {
        if(getActivity() instanceof AppCompatActivity) {
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            if (activity.getSupportActionBar() != null) {
                activity.getSupportActionBar() .setDisplayHomeAsUpEnabled(true);
                activity.getSupportActionBar().setDisplayShowHomeEnabled(true);

                activity.getSupportActionBar().setTitle(getString(R.string.product_detail));
            }
        }
    }

    private void attachPresenter() {
        presenter.attachView(this);
    }

    private void initUi() {
        presenter.loadProduct(getProductIdFromIntent());
    }

    private Long getProductIdFromIntent() {
        return getActivity().getIntent().getLongExtra("productId", 0);
    }

    public void handleBackAction() {
        presenter.onBackClicked(binding.etToastAlSalir.getText().toString());
    }

    @Override
    public void bind(Product product) {
        binding.tvProduct.setText(product.getTitle());
        binding.tvDescription.setText(product.getDescription());
        imageLoader.loadImage(binding.ivProduct, product.getImage());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        binding = null;
    }
}