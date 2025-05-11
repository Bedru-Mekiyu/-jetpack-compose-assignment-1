
package com.example.courselist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.courselist.ui.theme.CourseListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CourseListTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CourseListScreen(courses = sampleCourses)
                }
            }
        }
    }
}

@Composable
fun CourseListScreen(courses: List<Course>) {
    LazyColumn(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(courses) { course ->
            CourseCard(course = course)
        }
    }
}

@Composable
fun CourseCard(course: Course) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = course.title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = course.code,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "${course.creditHours} credits",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                IconButton(
                    onClick = { isExpanded = !isExpanded },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = if (isExpanded) "Show less" else "Show more",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            if (isExpanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Column(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Description:",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = course.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Prerequisites:",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = course.prerequisites.ifEmpty { "None" },
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

data class Course(
    val title: String,
    val code: String,
    val creditHours: Int,
    val description: String,
    val prerequisites: String
)

val sampleCourses = listOf(
    Course(
        title = "Introduction to Computer Science",
        code = "CS 101",
        creditHours = 4,
        description = "Fundamental concepts of computer science including problem solving, algorithms, abstraction, and software design.",
        prerequisites = ""
    ),
    Course(
        title = "Data Structure and Algorithm",
        code = "CS 201",
        creditHours = 4,
        description = "Study of fundamental data structures including arrays, linked lists, stacks, queues, trees, and graphs.",
        prerequisites = "CS 101"
    ),
    Course(
        title = "Fundamentals of Cyber security",
        code = "SITE-3131",
        creditHours = 5,
        description = "This course provides a one-semester overview of cybersecurity. It is designed to help students\n" +
                "with prior computer and programming knowledge — both undergraduate and graduate —\n" +
                "understand this important priority in society today.\n" +
                "The course gives a broad overview of essential concepts and methods for implementing and\n" +
                "evaluating cybersecurity in information systems. The course dominantly focuses on technical\n" +
                "aspects of cybersecurity. In addition to its technical content, the course addresses the\n" +
                "importance of managerial, human and physical security controls, and the impact of\n" +
                "cybersecurity in businesses, organizations, individuals and society.",
        prerequisites = "CS 201"
    ),
    Course(
        title = "fundamental ot Database",
        code = "CS 401",
        creditHours = 3,
        description = "Principles of database systems including data modeling, relational databases, SQL, transactions, and database design.",
        prerequisites = "CS 201"
    ),
    Course(
        title = "Operating Systems and System Programming ",
        code = "SECT-3082",
        creditHours = 7,
        description = " Operating systems are an essential part of any computer system. Similarly, a\n" +
                " course on operating systems is an essential part of any computer science edu\n" +
                "cation. This field is undergoing rapid change, as computers are now prevalent\n" +
                " in virtually every arena of day-to-day life—from embedded devices in auto\n" +
                "mobiles through the most sophisticated planning tools for governments and\n" +
                " multinational firms Principles of operating systems including processes, threads, memory management, file systems, and security.",
        prerequisites = "CS 201"
    ),
    Course(
        title = "Networking",
        code = "CS 403",
        creditHours = 5,
        description = "Fundamentals of computer networking including protocol layers, routing, transport protocols, and network security.",
        prerequisites = "CS 201"
    ),
    Course(
        title = "Fundamentals of Software Engineering ",
        code = "SITE-2103",
        creditHours = 7,
        description = "Principles of software engineering including requirements analysis, design, implementation, testing, and maintenance. As soon as computers were invented, the ability to tell them what to do made programming an awesome task. Programmers used a technique called \"code and fix” write some code, and fix it when it breaks. But things started getting too compacted too soon. Writing bigger software through the so-called \"code and fix\" mechanism had its problems. The inability to assess progress, quality, or risks, the difficulty to accommodate changes without a major design overhaul, and the unclear delivery features, timing, and support made \"code and fix\" strategy is difficult to use in larger projects.  \n" +
                "Therefore, software engineering emerged as a discipline to manage software projects. The activities in software engineering include defining what the software should do (software requirements), describing the solution specified in the requirements on paper (design), writing software that meets the solution using a programming language (implementation), making sure that the implementation meets the design and requirements (testing), and progressively modifying the software (maintenance and evolution). But software engineering is not just a prerequisite to coding. It also defines the way we communicate and keep stream of our tasks, it is the way we collaborate too.\n" +
                "The key idea that software engineering introduced in projects is traceability. The things written in the requirements must have a corresponding solution described in the design document, and a piece of code that implements a certain artifact on the design.\n" +
                "This course introduces software engineering though a project based delivery strategy and you are expected to apply what you studied in classes on your projects\n",
        prerequisites = "CS 201"
    ),
    Course(
        title = "Fundamental of Artificial Intelligence",
        code = "CS 501",
        creditHours = 4,
        description = "Introduction to artificial intelligence including search, knowledge representation, machine learning, and natural language processing.",
        prerequisites = "CS 301"
    ),
    Course(
        title = "Machine Learning",
        code = "CS 502",
        creditHours = 4,
        description = "Fundamentals of machine learning including supervised and unsupervised learning, neural networks, and deep learning.",
        prerequisites = "CS 301, MATH 202"
    ),
    Course(
        title = "Computer Graphics",
        code = "CS 503",
        creditHours = 3,
        description = " Principles of computer graphics including 2D and 3D graphics, rendering, animation, and GPU programming. Application areas of Computer Graphics, overview of graphics systems, video-display devices, and raster-scan systems, random scan systems, graphics monitors and work stations and input devices.  Points and lines, line drawing algorithms, mid-point circle and ellipse algorithms. Filled area primitives: Scan line polygon fill algorithm, boundary-fill and flood-fill algorithms \n" +
                "\n",
        prerequisites = "CS 201, Aplied Maths"
    )
)

@Preview(showBackground = true)
@Composable
fun CourseCardPreview() {
    CourseListTheme {
        CourseCard(course = sampleCourses[0])
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CourseCardDarkPreview() {
    CourseListTheme {
        CourseCard(course = sampleCourses[0])
    }
}

@Preview(showBackground = true)
@Composable
fun CourseListPreview() {
    CourseListTheme {
        CourseListScreen(courses = sampleCourses)
    }
}