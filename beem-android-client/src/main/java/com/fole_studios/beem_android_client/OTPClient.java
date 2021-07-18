package com.fole_studios.beem_android_client;

public class OTPClient
{
    private String _otpPinId;
    private String _phoneNumber;
    public boolean _isPin;

    /**
     *
     * @param phoneNumber
     * Number to receive OTP pin.
     */
    public OTPClient(String phoneNumber)
    {
        _phoneNumber = phoneNumber;
    }

    /**
     * Initializes processes for sending OTP pin to user.
     */
    public void run()
    {
        initOTP();
    }

    /**
     * Checks if the pin is valid.
     * @param pin
     * Numbers received as pin to user using sms
     */
    public void pinValidity(String pin)
    {
        verifyOTP(pin);
    }

    /**
     * Initializes request to the Beem's OTP service so as to receive a <code>pinId</code>.
     * <code>pinId</code> is used to check the OTP pin validity.
     */
    private void initOTP()
    {
        new OTPDataTask()
        {
            @Override
            public void onPinReceived(String pinId)
            {
                _otpPinId = pinId;
            }
        }.execute(_phoneNumber);

    }

    /**
     * Verifies the given pin
     * @param pin
     * Numbers received as pin to user using sms
     */
    private void verifyOTP(String pin)
    {
        new VerifyOTPTask()
        {
            @Override
            public void onPinReceived(String code)
            {
                validateCode(code);
            }
        }.execute(_otpPinId, pin);
    }

    /**
     * Returns <code>true/false</code> of pin validity.
     * @param code
     * Code received after checking pin validity from the web service. <br>
     *<code>117</code> - Success <br> <code>114</code> - Failure
     */
    private void validateCode(String code)
    {
        int _code = Integer.parseInt(code);

        if(_code == 117)
        {
            //valid pin
            _isPin = true;
        }
        else if(_code == 114)
        {
            //invalid pin
            _isPin = false;
        }
    }

}
