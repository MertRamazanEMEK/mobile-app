package com.onur.fastproudsearch;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductRecyclerViewAdaptor extends RecyclerView.Adapter<ProductRecyclerViewAdaptor.ProductViewHolder> {

    private List<Product> productList;
    class ProductViewHolder extends RecyclerView.ViewHolder{
        private AppCompatImageView productAppCompatImageView;
        private AppCompatTextView titleAppCompatTextView,descriptionAppCompatTextView,priceAppCompatTextView;

        ProductViewHolder(View view){
            super(view);
            productAppCompatImageView=view.findViewById(R.id.productAppCompatImageView);
            titleAppCompatTextView=view.findViewById(R.id.titleProductAppCompatTextView);
            descriptionAppCompatTextView=view.findViewById(R.id.descriptionProductAppCompatTextView);
            priceAppCompatTextView=view.findViewById(R.id.priceProductAppCompatTextView);
        }

    }

    public ProductRecyclerViewAdaptor(List<Product> productList){
        this.productList=productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_product,parent,false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product=productList.get(position);
        holder.productAppCompatImageView.setImageURI(Uri.parse(product.getImages().get(0)));
        Picasso.get()
                .load(product.getImages().get(0))
                .into(holder.productAppCompatImageView);
        holder.titleAppCompatTextView.setText(product.getTitle());
        holder.descriptionAppCompatTextView.setText(product.getDescription());
        holder.priceAppCompatTextView.setText(product.getPrice().toString());
    }

    public int getItemCount(){
        return productList.size();
    }
}
