package edu.miu.quiz.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import edu.miu.quiz.R
import edu.miu.quiz.db.Quiz
import edu.miu.quiz.db.QuizDatabase
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splash, container, false)
        val demoQuiz1 = Quiz(1L,"1. Yeast are most likely to grow in frozen fruits during","A. slow thawing", "B. refrigeration","C. ambient temperature","D. none of these","a")
        val demoQuiz2 = Quiz(2L,"2) Watery soft rot is found mostly in","A. fruits", "B. vegetables","C. cereals","D. all of these","b")
        val demoQuiz3 = Quiz(3L,"3) Black mold rot is caused by","A. flavus", "B. niger","C. Trichoderma","D. Trichothecium roseum","b")
        val demoQuiz4 = Quiz(4L,"4) Concentrate of fruits and vegetable juices","A. favor the growth of A. niger and A. flavus species", "B. favour the growth of yeast and of acid and sugar tolerant Leuconostoc and Lactobacillus species","C. favor the growth of Saprophytic bacteria","D. none of the above","b")
        val demoQuiz5 = Quiz(5L,"5) The predominant micro-organisms in frozen foods are ","A. bacteria", "B. micro-coccus","C. yeast and moulds","D. none of these","c")
        val demoQuiz6 = Quiz(6L,"6) Anthracnose is a defect which can be observed as ","A. spotting of leaves", "B. spotting of seedpods","C. spotting of fruits","D. all of these","d")
        val demoQuiz7 = Quiz(7L,"7) Stem and rots caused by species of molds involve ","A. leaves of fruits", "B. stem ends of fruits","C. spotting on vegetables","D. spotting on fruits","b")
        val demoQuiz8 = Quiz(8L,"8) Species of __________ are predominant on thawing vegetables like sweet corn and peas when the temperature of thawing is fairly low ","A. Streptococcus", "B. Micrococcus","C. both (a) and (b)","D. none of these","b")
        val demoQuiz9 = Quiz(9L,"9) Under what conditions food poisoning bacteria may grow and produce toxins in vegetables? ","A. When thawed vegetables are held at refrigerated temperature for any considerable period", "B. When thawed vegetables are held at room temperature for any considerable period","C. When thawed vegetables are held below refrigerated temperature for any considerable period","D. none of these","b")
        val demoQuiz10 = Quiz(10L,"10) Bacterial soft rot is caused due to ","A. fermentation of pectin", "B. fermentation of sugars","C. formation of ketones","D. formation of amino acids","a")
        val demoQuiz11 = Quiz(11L,"11) Saprophytic bacteria cause ","A. sliminess or souring in piled, wet and heating vegetables", "B. brown rot in vegetables","C. black rot in fruits","D. bacterial soft rot","a")
        val demoQuiz12 = Quiz(12L,"12) The micro-organism which is present in both fresh and frozen juices is ","A. E. coli", "B. Entereobacter aerogenes","C. P. chrysogenum","D. none of these","b")
        val demoQuiz13 = Quiz(13L,"13) The gram-negative organisms is ","A. Actinomyces", "B. Bacillus","C. Clostridium","D. none of these","d")
        val demoQuiz14 = Quiz(14L,"14) Catalase production is negative in which of the following? ","A. Streptococcus", "B. Salmonella","C. Proteus","D. Staphylococcus","a")
        val demoQuiz15 = Quiz(15L,"15) Which of the following is a substitute for crystal violet used in gram-staining procedure? ","A. Methylene blue", "B. Bromocresol green","C. Safranin","D. Phenolpthalene","a")

        launch {
            context?.let {
                QuizDatabase(it)
                    .getQuizDao().deleteAllQuiz()
                QuizDatabase(it)
                    .getQuizDao().addQuizzes(demoQuiz1,demoQuiz2,demoQuiz3,demoQuiz4,demoQuiz5,demoQuiz6,demoQuiz7,demoQuiz8,demoQuiz9,demoQuiz10,demoQuiz11,demoQuiz12,demoQuiz13,demoQuiz14,demoQuiz15)
            }
        }
        return view
    }

    override fun onResume(){
        super.onResume()
        Navigation.findNavController(requireView()).navigate(R.id.action_splashFragment_to_homeFragment)
    }

}