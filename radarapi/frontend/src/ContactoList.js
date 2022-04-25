import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

class ContactoList extends Component {

    constructor(props) {
        super(props);
        this.state = {contacto_estrecho: []};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        fetch('/contactos')
            .then(response => response.json())
            .then(data => this.setState({contacto_estrecho: data}));
    }

    async remove(id) {
        await fetch(`/contactos/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedcontactos = [...this.state.contacto_estrecho].filter(i => i.id !== id);
            this.setState({contacto_estrecho: updatedcontactos});
        });
    }

    render() {
        const {contacto_estrecho} = this.state;

        const  contactosList = contacto_estrecho.map(contacto_estrecho => {
            return <tr key={contacto_estrecho.id}>
                <td style={{whiteSpace: 'nowrap'}}>{contacto_estrecho.id}</td>
                <td>{contacto_estrecho.id_usu1}</td>
                <td>{contacto_estrecho.id_usu2}</td>
                <td>{contacto_estrecho.duracion}</td>
                <td>{contacto_estrecho.caducidad}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/contactos/" + contacto_estrecho.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(contacto_estrecho.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/contactos/new">Add Contact</Button>
                    </div>
                    <h3>Contactos</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="15%">Id</th>
                            <th width="15%">Id Usuario 1</th>
                            <th width="15%">Id Usuario 1</th>
                            <th width="15%">Duración</th>
                            <th width="10%">Fecha Caducidad</th>
                        </tr>
                        </thead>
                        <tbody>
                        {contactosList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default ContactoList;