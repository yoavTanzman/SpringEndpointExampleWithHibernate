import React, { Component } from "react";
import { Router, Switch, Route } from "react-router-dom";
import history from './history';
import Home from './component/Home'
import GetCustomer from './component/GetCustomer'
import GetAllUsers from './component/GetAllUsers'
export default class Routes extends Component {
    render() {
        return (
            <Router history={history}>
                <Switch>
                    <Route path="/" exact component={Home} />
                    <Route path="/Home" component={Home} />
                    <Route path="/GetCustomer" component={GetCustomer} />
                    <Route path="/GetAllUsers" component={GetAllUsers} />
                </Switch>
            </Router>
        )
    }
}