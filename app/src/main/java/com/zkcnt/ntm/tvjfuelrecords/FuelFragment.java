package com.zkcnt.ntm.tvjfuelrecords;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FuelFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fuel, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initCalculateBtn();
    }

    void checkField() {
        // Remain
        EditText _remainLeft = getView().findViewById(R.id.remain_left);
        EditText _remainCenter = getView().findViewById(R.id.remain_center);
        EditText _remainRight = getView().findViewById(R.id.remain_right);
        EditText _remainTotal = getView().findViewById(R.id.remain_total);
        EditText _remainUplift = getView().findViewById(R.id.remain_uplift);
        EditText _remainDensity = getView().findViewById(R.id.remain_density);
        EditText _remainUpliftDensity = getView().findViewById(R.id.remain_uplift_density);
        EditText _remainSum = getView().findViewById(R.id.remain_sum);

        // Finalremain
        EditText _finalremainLeft = getView().findViewById(R.id.finalremain_left);
        EditText _finalremainCenter = getView().findViewById(R.id.finalremain_center);
        EditText _finalremainRight = getView().findViewById(R.id.finalremain_right);
        EditText _finalremainLeftCenterRight = getView().findViewById(R.id.finalremain_left_center_right);
        EditText _finalremainTolerance = getView().findViewById(R.id.tolerance);

        // Remain String
        String _remainLeftStr = _remainLeft.getText().toString();
        String _remainCenterStr = _remainCenter.getText().toString();
        String _remainRightStr = _remainRight.getText().toString();
        String _remainUpliftStr = _remainUplift.getText().toString();
        String _remainDensityStr = _remainDensity.getText().toString();

        // Finalremain String
        String _finalremainLeftStr = _finalremainLeft.getText().toString();
        String _finalremainCenterStr = _finalremainCenter.getText().toString();
        String _finalremainRightStr = _finalremainRight.getText().toString();

        // Check
        if(_remainLeftStr.isEmpty() || _remainCenterStr.isEmpty() || _remainRightStr.isEmpty() || _remainUpliftStr.isEmpty() || _remainDensityStr.isEmpty() ||
                _finalremainLeftStr.isEmpty() || _finalremainCenterStr.isEmpty() || _finalremainRightStr.isEmpty()) {
            Toast.makeText(getActivity(), "กรุณากรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_LONG).show();
            Log.d("CHECK_FIELD", "FIELD IS EMPTY");
        } else {
            // Remain Int
            int _remainLeftInt = Integer.parseInt(_remainLeftStr);
            int _remainCenterInt = Integer.parseInt(_remainCenterStr);
            int _remainRightInt = Integer.parseInt(_remainRightStr);
            int _remainUpliftInt = Integer.parseInt(_remainUpliftStr);
            double _remainDensityDouble = Double.parseDouble(_remainDensityStr);

            // Finalremain Int
            int _finalremainLeftInt = Integer.parseInt(_finalremainLeftStr);
            int _finalremainCenterInt = Integer.parseInt(_finalremainCenterStr);
            int _finalremainRightInt = Integer.parseInt(_finalremainRightStr);

            int _resultTotal = calculateTotal(_remainLeftInt, _remainCenterInt, _remainRightInt);
            double _resultUpliftDensity = calculateUpliftDensity(_remainUpliftInt, _remainDensityDouble);
            long _resultUpliftDensityLong =  Math.round(_resultUpliftDensity);
            int _resultUpliftDensityInt = (int) _resultUpliftDensityLong;

            int _resultSum = _resultTotal + _resultUpliftDensityInt;

            int _resultFinalLeftCenterRight = calculateFinal(_finalremainLeftInt, _finalremainCenterInt, _finalremainRightInt);
            int _resultTolerance = _resultSum - _resultFinalLeftCenterRight;

            _remainTotal.setText(String.format("%d", _resultTotal));
            _remainUpliftDensity.setText(String.format("%d", _resultUpliftDensityInt));
            _remainSum.setText(String.format("%d", _resultSum));
            _finalremainLeftCenterRight.setText(String.format("%d", _resultFinalLeftCenterRight));
            _finalremainTolerance.setText(String.format("%d", _resultTolerance));
        }
    }

    int calculateTotal(int left, int center, int right) {
        return left + center + right;
    }

    double calculateUpliftDensity(int uplift, double density) {
        return uplift * density;
    }

    int calculateFinal(int left, int center, int right) {
        return left + center + right;
    }

    void initCalculateBtn() {
        Button _cal = getView().findViewById(R.id.fuel_calculate);
        _cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkField();
            }
        });
    }
}