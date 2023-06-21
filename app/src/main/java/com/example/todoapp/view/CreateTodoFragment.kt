package com.example.todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.todoapp.R
import com.example.todoapp.model.Todo
import com.example.todoapp.util.NotificationHelper
import com.example.todoapp.util.TodoWorker
import com.example.todoapp.viewmodel.DetailTodoViewModel
import java.util.concurrent.TimeUnit

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateTodoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateTodoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_todo, container, false)
    }
    private lateinit var viewModel: DetailTodoViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this).get(DetailTodoViewModel::class.java)

        val btnAdd = view.findViewById<Button>(R.id.btnAdd)

        btnAdd.setOnClickListener {
            val myWorkRequest = OneTimeWorkRequestBuilder<TodoWorker>()
                .setInitialDelay(3, TimeUnit.SECONDS)
                .setInputData(
                    workDataOf(
                        "title" to "Todo Created",
                        "message" to "A new todo has been created! Stay focus!"
                    )
                ).build()

            val txtTitle = view.findViewById<EditText>(R.id.txtToDoTitle)
            val txtNotes = view.findViewById<EditText>(R.id.txtToDoNotes)
            val radPriority = view.findViewById<RadioGroup>(R.id.radioGPriority)
            val radio = view.findViewById<RadioButton>(radPriority.checkedRadioButtonId)
            var todo = Todo(txtTitle.text.toString(), txtNotes.text.toString(), radio.tag.toString().toInt())
            val list = listOf(todo)
            viewModel.addTodo(list)
            Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()
            WorkManager.getInstance(requireContext()).enqueue(myWorkRequest)
            Navigation.findNavController(it).popBackStack()
        }

    }

}