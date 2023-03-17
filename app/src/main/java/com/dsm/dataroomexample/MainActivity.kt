package com.dsm.dataroomexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputBinding
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.dsm.dataroomexample.adapter.AdapterListener
import com.dsm.dataroomexample.adapter.AdapterUsers
import com.dsm.dataroomexample.context.DBContext
import com.dsm.dataroomexample.databinding.ActivityMainBinding
import com.dsm.dataroomexample.model.User
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), AdapterListener {
    lateinit var binding: ActivityMainBinding

    var userList: MutableList<User> = mutableListOf()
    lateinit var adapter: AdapterUsers
    lateinit var room: DBContext
    lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvUsuarios.layoutManager = LinearLayoutManager(this)
        room = Room.databaseBuilder(this, DBContext::class.java, "dbPrueba").build()

        getUsers(room)

        binding.btnAddUpdate.setOnClickListener{
            if(binding.etUsuario.text.isNullOrEmpty() || binding.etPais.text.isNullOrEmpty()){
                Toast.makeText(this, "DEBE LLENAR TODOS LOS CAMPOS", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if(binding.btnAddUpdate.text.equals("agregar")){
                user = User(
                    binding.etUsuario.text.toString().trim(),
                    binding.etPais.text.toString().trim()
                )
                setUser(room, user)
            }else if (binding.btnAddUpdate.text.equals("actualizar")){
                user.country = binding.etPais.text.toString().trim()
                updateUser(room, user)
            }
        }
    }
    private fun getUsers(room: DBContext){
        lifecycleScope.launch{
            userList = room.daoUser().getUsers()
            adapter = AdapterUsers(userList, this@MainActivity)
            binding.rvUsuarios.adapter = adapter
        }
    }

    private fun setUser(room: DBContext, user: User){
        lifecycleScope.launch{
            room.daoUser().addUser(user)
            getUsers(room)
            clearFields()
        }
    }

    private fun updateUser(room: DBContext, user: User){
        lifecycleScope.launch{
            room.daoUser().updateUser(user.user, user.country)
            getUsers(room)
            clearFields()
        }
    }

    private fun clearFields(){
        user.user = ""
        user.country = ""
        binding.etUsuario.setText("")
        binding.etPais.setText("")

        if(binding.btnAddUpdate.text.equals("actualizar")){
            binding.btnAddUpdate.setText("agregar")
            binding.etUsuario.isEnabled = true
        }
    }

    override fun onEditItemClick(user: User) {
        binding.btnAddUpdate.setText("actualizar")
        binding.etUsuario.isEnabled = false
        this.user = user
        binding.etUsuario.setText(user.user)
        binding.etPais.setText(user.country)
    }

    override fun onDeleteItemClick(user: User) {
        lifecycleScope.launch{
            room.daoUser().deleteUser(user.user)
            getUsers(room)
        }
    }
}










