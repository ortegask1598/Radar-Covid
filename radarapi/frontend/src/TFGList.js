import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

class TFGList extends Component {

    constructor(props) {
        super(props);
        this.state = {usuarios: []};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        fetch('/usuarios')
            .then(response => response.json())
            .then(data => this.setState({usuarios: data}));
    }

    async remove(id) {
        await fetch(`/usuarios/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedusuarios = [...this.state.usuarios].filter(i => i.id !== id);
            this.setState({usuarios: updatedusuarios});
        });
    }

    render() {
        const {usuarios} = this.state;

        const usuarioList = usuarios.map(usuario => {
            return <tr key={usuario.id}>
                <td style={{whiteSpace: 'nowrap'}}>{usuario.email}</td>
                <td>{usuario.contraseña}</td>
                <td>{usuario.positivo}</td>
                <td>{usuario.contactos}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/usuarios/" + usuario.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(usuario.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/usuarios/new">Add User</Button>
                    </div>
                    <h3>Users</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="15%">Id</th>
                            <th width="15%">Email</th>
                            <th width="15%">Contraseña</th>
                            <th width="15%">Positivo</th>
                            <th width="10%">Contactos</th>
                        </tr>
                        </thead>
                        <tbody>
                        {usuarioList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default TFGList;