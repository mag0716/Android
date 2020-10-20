package com.github.mag0716.multipledevicesupportsample.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.mag0716.multipledevicesupportsample.R
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usernameEditText = view.findViewById<TextInputLayout>(R.id.username)
        val passwordEditText = view.findViewById<TextInputLayout>(R.id.password)
        val loginButton = view.findViewById<Button>(R.id.login)
        val loadingProgressBar = view.findViewById<ProgressBar>(R.id.loading)

        loginViewModel.isProgressing.observe(viewLifecycleOwner) {
            loadingProgressBar.isVisible = it
        }
        loginViewModel.loginResult.observe(viewLifecycleOwner) {
            //TODO: 画面遷移
        }

        passwordEditText.editText?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginViewModel.login(
                    usernameEditText.editText?.text.toString(),
                    passwordEditText.editText?.text.toString()
                )
            }
            false
        }

        loginButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            loginViewModel.login(
                usernameEditText.editText?.text.toString(),
                passwordEditText.editText?.text.toString()
            )
        }
    }
}