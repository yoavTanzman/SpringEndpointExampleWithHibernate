import React, { Component } from 'react';

import MyNavbar from '../MyNavbar'
import { Container } from 'react-bootstrap';
import axios from 'axios'

const getAllUsersApi=axios.create({ 
    baseURL:'http://localhost:7074/customer'

})

class index extends Component {

    constructor(props){
        super(props);
        this.state={
            userList:[]
        }
    }
    componentDidMount(){
        
        getAllUsersApi.get('/get-all-users').then(res=>{
            const user = res.data;
            console.log(res)
            this.addUserToList(user);
        }).catch(error=>{
    
            console.log(error)
        })
    }



    addUserToList=(props)=>{
        this.setState({userList: []});
        props.map(user=>{
                // let userName =user.firstName;
                // let userLastName =user.lastName;
                // let userId= user.userId;
                // let dat
                this.setState({
                       
                    userList:[...this.state.userList, user]
            })
        })
    }

    render() {
        return (
            <Container>
                <MyNavbar/>
                <table id="testNames"className="table">
                            <thead className="thead-dark">
                                    <tr>
                                    
                                    <th scope="col">UserId</th>
                                    <th scope="col">UserName</th>
                                    <th scope="col">UserLastName</th>
                                    </tr>
                            </thead>
                            <tbody>
                                        {
                                             this.state.userList.map(item=>{
                                        
                                                 return(
                                                    <tr key={item.uniqeid}>
                                                    <td>{item.userId}</td>
                                                    <td>{item.firstName}</td>
                                                    <td>{item.lastName}</td>                            
                                                    </tr>
                                                    
                                                    )
                                              })
                                         }
                    
                         </tbody>

                </table>


            
               </Container>
        );
    }
}

export default index;