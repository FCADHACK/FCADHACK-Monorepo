import React, { useState } from 'react'
import './AuthPage.css'
import AuthInput from './components/AuthInput/AuthInput'
import Button from '../../components/Button/Button.jsx'
import axios from 'axios'
import { Link, useNavigate } from "react-router-dom";


import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const AuthPage = () => {
  const [login, setLogin] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();


const fetchURLReg = "http://193.22.147.81:8083/api/identity/registration";
const fetchURLAuth = "http://193.22.147.81:8083/api/identity/auth";


const onAuth = (e) => {
  e.preventDefault();
  if (password !== "" && login !== "") {
      axios
        .post(
          fetchURLReg,
          {
            username: login,
            password: password,
            confirmPassword: password,
            role: {
              name: "User",
            },
          },
          {
            headers: {
              Accept: "application/json",
              "Content-Type": "application/json",
            },
          }
        )
        .then((response) => {
          axios
            .post(
              fetchURLAuth,
              {
                username: login,
                password: password,
              },
              {
                headers: {
                  Accept: "application/json",
                  "Content-Type": "application/json",
                },
              }
            )
            .then((response) => {
               toast.success("Получилось!");
               navigate("/");
            })
            .catch((error) => {
              toast.error("Ошибка");
              console.error(error);
            });
        })
        .catch((error) => {
          axios
            .post(
              fetchURLAuth,
              {
                username: login,
                password: password,
              },
              {
                headers: {
                  Accept: "application/json",
                  "Content-Type": "application/json",
                },
              }
            )
            .then((response) => {
              toast.success("Получилось!");
              navigate("/");
            })
            .catch((error) => {
              toast.error("Ошибка");
              console.error(error);
            });
        });
  } else {
    toast.error("Введите данные")
  }

    
}

  return (
    <div className="auth_container">
      <div className="auth_wrapper">
        <div className="sideBar_block">
          <div className="sideBar_inner">
            <Link to="/">
              <h1 className="auth_logo">FCADHACK</h1>
            </Link>
            <div className="authform_block">
              <h1>
                Добро пожаловать обратно! Пожалуйста, войдите в<br /> свой
                аккаунт.
              </h1>
              <form className="auth_form" action="">
                <AuthInput
                  onChange={(e) => setLogin(e.target.value)}
                  required
                  type="text"
                  placeholder={"Логин"}
                />
                <AuthInput
                  onChange={(e) => setPassword(e.target.value)}
                  required
                  type="password"
                  placeholder={"Пароль"}
                />
                <div className="form_warn-block">
                  <div className="form_save-block">
                    <input
                      className="checkbox"
                      type="checkbox"
                      id="authcheckbox"
                    />
                    <label htmlFor="authcheckbox">
                      <span>Запомнить меня</span>
                    </label>
                  </div>
                  <span>Забыли пароль?</span>
                </div>
                <Button onClick={(e) => onAuth(e)}>Войти</Button>
              </form>
            </div>
          </div>
        </div>
        <ToastContainer />
        <div className="authImage_block">
          <img src="./men.svg" alt="menimage" />
        </div>
      </div>
    </div>
  );
}

export default AuthPage