package com.example.artspace5
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import androidx.lifecycle.compose.collectAsStateWithLifecycle

import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
data class Artwork(val image:Int,val title:String,val artist:String,val year:String)
val artworks=listOf(Artwork(R.drawable.art1,"Пшеничное поле с кипарисами","Винсент ван Гог","1889"),Artwork(R.drawable.art2,"Автопортрет в соломенной шляпе","Винсент ван Гог","1887"),Artwork(R.drawable.art3,"Олеандры","Винсент ван Гог","1888"))
data class ArtUiState(val index:Int=0)
class ArtViewModel: ViewModel(){
 private val _uiState=MutableStateFlow(ArtUiState())
 val uiState=_uiState.asStateFlow()
 fun next(){_uiState.update{it.copy(index=(it.index+1)%artworks.size)}}
 fun previous(){_uiState.update{it.copy(index=(it.index+artworks.size-1)%artworks.size)}}
}
class MainActivity:ComponentActivity(){override fun onCreate(savedInstanceState:Bundle?){super.onCreate(savedInstanceState);enableEdgeToEdge();setContent{MaterialTheme{ArtScreen()}}}}
@Composable fun ArtScreen(){
 var index by rememberSaveable{mutableIntStateOf(0)}
 val next:()->Unit={index=(index+1)%artworks.size};val previous:()->Unit={index=(index+artworks.size-1)%artworks.size}

 val art=when(index){0->artworks[0];1->artworks[1];else->artworks[2]}
 val landscape=LocalConfiguration.current.screenWidthDp>LocalConfiguration.current.screenHeightDp
 Surface(Modifier.fillMaxSize()){
 if(landscape) Row(Modifier.safeDrawingPadding().padding(24.dp),horizontalArrangement=Arrangement.spacedBy(28.dp),verticalAlignment=Alignment.CenterVertically){
 ArtImage(art,Modifier.weight(1f).fillMaxHeight());ArtInfo(art,index,previous,next,Modifier.weight(1f))
 }else Column(Modifier.safeDrawingPadding().padding(24.dp),verticalArrangement=Arrangement.spacedBy(24.dp)){
 Text("Art Space",style=MaterialTheme.typography.headlineLarge)
 ArtImage(art,Modifier.weight(1f).fillMaxWidth());ArtInfo(art,index,previous,next,Modifier.fillMaxWidth())
 }
 }
}
@Composable fun ArtImage(art:Artwork,modifier:Modifier){Card(modifier){Image(painterResource(art.image),art.title,Modifier.fillMaxSize().padding(16.dp),contentScale=ContentScale.Fit)}}
@Composable fun ArtInfo(art:Artwork,index:Int,previous:()->Unit,next:()->Unit,modifier:Modifier){Column(modifier,verticalArrangement=Arrangement.spacedBy(16.dp)){
 Text(art.title,style=MaterialTheme.typography.headlineMedium)
 Text("${art.artist} · ${art.year}",style=MaterialTheme.typography.bodyLarge)
 Text("${index+1} из ${artworks.size}")
 Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.spacedBy(12.dp)){OutlinedButton(previous,Modifier.weight(1f).testTag("previous")){Text("Назад")};Button(next,Modifier.weight(1f).testTag("next")){Text("Далее")}}
}}
