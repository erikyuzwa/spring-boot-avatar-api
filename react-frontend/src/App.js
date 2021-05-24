import { useEffect, useState } from "react";
import { createAvatar, updateAvatar, deleteAvatar, getAllAvatars, getAvatar, getUser } from "./services";

const App = () => {
  const [user, setUser] = useState({});
  const [mode, setMode] = useState('Create');
  const [avatars, setAvatars] = useState([]);
  const [formAvatarId, setFormAvatarId] = useState(null);
  const [formAvatarName, setFormAvatarName] = useState('');
  const [formAvatarRace, setFormAvatarRace] = useState('');
  const [formAvatarClass, setFormAvatarClass] = useState('');
  const [dirty, setDirty] = useState(false);

  const dropAvatar = (id, name) => {
    if (window.confirm(`Are you sure you want to delete ${name}?`)) {
      deleteAvatar({userId: user.id, avatarId: id});
      console.log(`avatar ${name}:${id} deleted`);
      setDirty(true);
    }
  };

  const resetAvatarForm = () => {
    setFormAvatarId(null);
    setFormAvatarName('');
    setFormAvatarClass('');
    setFormAvatarRace('');
  };

  const editAvatar = (id) => {
    getAvatar({userId: user.id, avatarId: id}).then(response => {
      setFormAvatarId(response.id);
      setFormAvatarName(response.name);
      setFormAvatarClass(response.classType);
      setFormAvatarRace(response.raceType);
      setMode("Update");
    });
  };

  const createOrSave = () => {
    const data = {
      name: formAvatarName,
      raceType: formAvatarRace,
      classType: formAvatarClass
    };
    if (mode === 'Create') {
      createAvatar({userId: user.id, data: data}).then(response => {
        setDirty(true);
      });
    } else {
      updateAvatar({userId: user.id, avatarId: formAvatarId, data: data}).then(response => {
        setMode("Create");
        setDirty(true);
      });
    }
  };

  useEffect(() => {
    if (dirty) {
      resetAvatarForm();
      getAllAvatars({ userId: user.id })
        .then(items => {
          setAvatars(items);
          setDirty(false);
        });
    }
  }, [dirty, user.id]);

  useEffect(() => {
    getUser({ userId: 1 })
      .then(data => {
        setUser(data);
        getAllAvatars({ userId: data.id })
          .then(items => {
            setAvatars(items);
          });
      });
  }, []);

  return (
    <div className="container">
      <div className="row">
        <div className="col5">
          <h1 className="text-center">Welcome, {user.username} </h1>
        </div>
      </div>
      <div className="row">
        <div className="col10">
          <h1>Avatar List</h1>
          <table className="table table-striped">
            <thead>
              <tr>
                <td>Id</td>
                <td>Name</td>
                <td>Race</td>
                <td>Class</td>
                <td>Date Created</td>
                <td>Date Modified</td>
                <td>Actions</td>
              </tr>
            </thead>
            <tbody>
              {avatars.map(item => {
                return (
                  <tr key={item.id}>
                    <td>{item.id}</td>
                    <td>{item.name}</td>
                    <td>{item.raceType}</td>
                    <td>{item.classType}</td>
                    <td>{item.dateCreated}</td>
                    <td>{item.dateModified}</td>
                    <td><button onClick={() => editAvatar(item.id)}>Edit</button> | <button onClick={() => dropAvatar(item.id, item.name)}>Delete</button></td>
                  </tr>
                )
              })}
            </tbody>
          </table>
        </div>
      </div>
      <div className="row justify-content-md-center">
        <div className="col-6">
          <form>
            <div className="form-group row">
              <label htmlFor="newName" className="col-sm-2 col-form-label">Name</label>
              <div className="col-sm-10">
                <input type="text" name="name" className="form-control" id="newName" value={formAvatarName} onChange={(e) => setFormAvatarName(e.target.value)}/>
              </div>
            </div>
            <div className="form-group row">
              <label htmlFor="formAvatarClassSelect" className="col-sm-2 col-form-label">Class Type</label>
              <div className="col-sm-10">
                <select value={formAvatarClass} id="formAvatarClassSelect" className="form-control" onChange={(e) => setFormAvatarClass(e.target.value)}>
                  <option value=""></option>
                  <option value="DRUID">Druid</option>
                  <option value="MAGE">Mage</option>
                  <option value="PALADIN">Paladin</option>
                  <option value="RANGER">Ranger</option>
                  <option value="THIEF">Thief</option>
                  <option value="WIZARD">Wizard</option>
                </select>
              </div>
            </div>
            <div className="form-group row">
              <label htmlFor="formAvatarRaceSelect" className="col-sm-2 col-form-label">Race Type</label>
              <div className="col-sm-10">
                <select value={formAvatarRace} onChange={(e) => setFormAvatarRace(e.target.value)} id="formAvatarRaceSelect" className="form-control">
                  <option value=""></option>
                  <option value="DWARF">Dwarf</option>
                  <option value="ELF">Elf</option>
                  <option value="HUMAN">Human</option>
                  <option value="ORC">Orc</option>
                </select>
              </div>
            </div>
            <div style={{marginTop: 2 + 'em'}} className="form-group row">
              <div className="col-sm-10">
                <button onClick={createOrSave} className="btn btn-outline-primary" type="button" color="primary">{mode} Avatar</button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}

export default App;
