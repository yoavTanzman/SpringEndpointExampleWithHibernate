import React, { Component } from 'react';
import {Container} from 'react-bootstrap'

import MyNavbar from '../MyNavbar'
import SimpleForm from './SimpleForm';

class index extends Component {
    render() {
        return (
            <Container>
                <MyNavbar/>
                <SimpleForm/>
            </Container>
        );
    }
}

export default index;